package constants;

public enum Numbers {
    API_ATTEMPTS_TO_GET_REQUEST(5);
    private final int value;

    Numbers(int value) {
        this.value = value;
    }

    public int toIntValue() {
        return value;
    }
}
