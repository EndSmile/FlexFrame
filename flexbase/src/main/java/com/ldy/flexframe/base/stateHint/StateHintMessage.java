package com.ldy.flexframe.base.stateHint;

import android.content.Context;

/**
 * Created by ldy on 2017/2/24.
 */

public class StateHintMessage {
    public final String message;
    public final StateHintRouter stateHintRouter;

    public StateHintMessage(String message, StateHintRouter stateHintRouter) {
        this.message = message;
        this.stateHintRouter = stateHintRouter;
    }

    /**
     * 只以message的内容作为判定条件
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateHintMessage that = (StateHintMessage) o;

        return message != null ? message.equals(that.message) : that.message == null;

    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }

    public interface StateHintRouter {
        void router(Context context);
    }
}
