package com.ldy.flexframe.base.stateHint;

import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedHashSet;

/**
 * Created by ldy on 2017/2/24.
 */

public class StateHintControl {
    private static StateHintControl instance;
    private LinkedHashSet<StateHintType> stateQueue = new LinkedHashSet<>();

    private StateHintType lastEmitState;

    private StateHintControl() {
    }

    public static StateHintControl getInstance() {
        if (instance == null) {
            synchronized (StateHintControl.class) {
                if (instance == null) {
                    instance = new StateHintControl();
                }
            }
        }
        return instance;
    }

    @Nullable
    public StateHintMessage getLastStateMessage() {
        if (lastEmitState != null) {
            return lastEmitState.getMessage();
        }
        return null;
    }

    /**
     * 插入提示
     *
     * @param stateHintType
     */
    public void pushStateHintType(StateHintType stateHintType) {
        operateAndEmit(stateHintType);
    }

    public void closeCurrent() {
        if (lastEmitState == null) {
            return;
        }
        operateAndEmit(lastEmitState.getCloseState());
    }

    private void operateAndEmit(StateHintType stateHintType) {
        operate(stateHintType);
        emitStateHint();
    }

    /**
     * <p>对传入的type在队列中操作</p>
     * <p>1.新传入的type原队列中没有这个优先级的,添加这个type</p>
     * <p>2.新传入的type原队列中有这个优先级的</p>
     * <p>  2.1.type是关闭消息,删除原队列中的相同优先级消息</p>
     * <p>  2.2.type是非关闭消息,替换原队列中的相同优先级消息</p>
     */
    private void operate(StateHintType stateHintType) {
        for (StateHintType type : stateQueue) {
            if (type.priorityEqual(stateHintType)) {
                stateQueue.remove(type);
                break;
            }
        }
        if (!stateHintType.isClose()) {
            stateQueue.add(stateHintType);
        }
    }

    /**
     * 发送状态提示消息,在队列中挑选出优先级最高的消息发出,如果没有,则发送关闭消息<p/>
     */
    private void emitStateHint() {
        StateHintType emitState = null;
        for (StateHintType stateHintType : stateQueue) {
            if (stateHintType.priorityEqualOrThan(emitState)) {
                emitState = stateHintType;
            }
        }

        if (emitState == null) {
            //如果待发送的消息为null,而上一次发了一个消息的话,则发送一个关闭消息
            if (lastEmitState != null) {
                EventBus.getDefault().post(new StateHintCloseMessage());
                lastEmitState = null;
            }
        } else {
            if (emitState.isClose()) {
                //由于在{@link #operate(StateHintType)}中不会添加isClose()为true的消息,所以这一分支可能不会被触发
                if (lastEmitState == null) {
                    //如果这次为第一次发送或上一次发送的是close消息,不向上发送消息
                    stateQueue.remove(emitState);
                } else {
                    lastEmitState = null;
                    EventBus.getDefault().post(new StateHintCloseMessage());
                    stateQueue.remove(emitState);
                }
            } else {
                if (emitState.equals(lastEmitState)) {
                    return;
                }
                lastEmitState = emitState;
                EventBus.getDefault().post(emitState.getMessage());
            }
        }
    }

}
