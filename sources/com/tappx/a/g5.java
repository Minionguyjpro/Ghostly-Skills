package com.tappx.a;

import android.content.Intent;

public class g5 extends z5 {
    private Intent b;

    public g5(q5 q5Var) {
        super(q5Var);
    }

    public String getMessage() {
        if (this.b != null) {
            return "User needs to (re)enter credentials.";
        }
        return super.getMessage();
    }
}
