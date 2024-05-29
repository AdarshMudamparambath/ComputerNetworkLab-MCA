class SimplexReceiver {
    public static void receiveFrame(String frame) {
        if (isFrameCorrupted(frame)) {
            System.out.println("Frame corrupted: " + frame);
        } else {
            System.out.println("Frame received successfully: " + frame);
        }
    }

    private static boolean isFrameCorrupted(String frame) {
        // Simple example: check if frame contains a lowercase letter
        for (char c : frame.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // No need for an actual main method in the receiver, as it receives frames directly from the sender
}
}
