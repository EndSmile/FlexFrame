package com.ldy.flexframe.base.stateHint;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by ldy on 2017/2/24.
 */
public class StateHintType {
    public static final int PRIORITY_TO_SERVER_NET = 5;
    private final StateHintMessage message;

    /**
     * 数值越大优先级越高
     */
    private int priority;
    private boolean isClose;

    /**
     * 对应优先级的关闭消息
     *
     * @param priority
     */
    public StateHintType(int priority) {
        this(priority, null, true);
    }

    public StateHintType(int priority, StateHintMessage message, boolean isClose) {
        this.message = message;
        this.priority = priority;
        this.isClose = isClose;
    }

    public boolean isClose() {
        return isClose;
    }

    public StateHintMessage getMessage() {
        return message;
    }

    public boolean priorityEqual(@Nullable StateHintType type) {
        if (type == null) {
            return false;
        }
        if (priority == type.priority) {
            return true;
        }
        return false;
    }

    public boolean priorityEqualOrThan(@Nullable StateHintType type) {
        if (type == null) {
            return true;
        }
        return priority >= type.priority;
    }

    public StateHintType getCloseState() {
        return new StateHintType(priority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateHintType that = (StateHintType) o;

        if (priority != that.priority) return false;
        if (isClose != that.isClose) return false;
        return message != null ? message.equals(that.message) : that.message == null;

    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + priority;
        result = 31 * result + (isClose ? 1 : 0);
        return result;
    }


    public static class ToServerNetStateBuilder {
        public static final int PRIORITY = PRIORITY_TO_SERVER_NET;

        public static StateHintType buildToServerErrType() {
            return new StateHintType(PRIORITY, new StateHintMessage("网络异常", new StateHintMessage.StateHintRouter() {
                @Override
                public void router(Context context) {
                    Toast.makeText(context, "当前网络存在异常,请尝试切换网络", Toast.LENGTH_SHORT).show();
                }
            }), false);
        }

        public static StateHintType buildToServerSuccessType() {
            return new StateHintType(PRIORITY);
        }
    }


}
