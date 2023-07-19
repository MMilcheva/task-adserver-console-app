package models.enums;

public enum Status {
    NEW,
    PENDING,
    COMPLETED,
    DISCARDED;

    @Override
    public String toString() {
        switch (this) {
            case NEW:
                return "New";
            case PENDING:
                return "Pending";
            case COMPLETED:
                return "Completed";
            case DISCARDED:
                return "Discarded";
            default:
                throw new UnsupportedOperationException("Can't convert status type.");
        }
    }
}
