package com.global.order.common.application.message;

import com.global.order.common.code.DlqType;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class DlqMessage {
    private DlqType type;
    private int failedCount = 0;
    private CustomErrorMessage error;

    public DlqMessage(DlqType type) {
        this.type = type;
    }

    public void increaseFailedCount() {
        this.failedCount++;
    }

    public void fail(CustomErrorMessage error) {
        this.error = error;
    }

    public boolean discard(int maxFailCount) {
        return this.failedCount > maxFailCount;
    }
}
