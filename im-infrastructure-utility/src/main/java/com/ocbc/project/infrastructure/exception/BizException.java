package com.ocbc.project.infrastructure.exception;

public class BizException extends RuntimeException implements ExceptionMessage{

    private final MessageCode messageCode;


    public BizException(MessageCode messageCode) {
        super(messageCode.getMessage());
        this.messageCode = messageCode;

    }

    public BizException(MessageCode messageCode, Throwable cause) {
        super(messageCode.getMessage(), cause);
        this.messageCode = messageCode;
    }

    public BizException(MessageCode messageCode, String... sub) {
        super(messageCode.getSubMessage(sub));
        this.messageCode = messageCode;
    }

    public BizException(MessageCode messageCode, Throwable cause, String... sub) {
        super(messageCode.getSubMessage(sub), cause);
        this.messageCode = messageCode;
    }

    @Override
    public MessageCode getMessageCode() {
        return this.messageCode;
    }
}
