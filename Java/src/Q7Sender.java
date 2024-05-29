import java.util.Random;

class SimplexSender {
    private static final int FRAME_SIZE = 4; // Size of each frame
    private static final int NOISE_PROBABILITY = 30; // Probability of noise (%)

    public static void main(String[] args) {
        String dataToSend = "Hello, world!";
        System.out.println("Sending data: " + dataToSend);
        sendFrames(dataToSend);
    }

    private static void sendFrames(String data) {
        for (int i = 0; i < data.length(); i += FRAME_SIZE) {
            String frame = data.substring(i, Math.min(i + FRAME_SIZE, data.length()));
            System.out.println("Sending frame: " + frame);
            String transmittedFrame = introduceNoise(frame);
            SimplexReceiver.receiveFrame(transmittedFrame);
        }
    }

    private static String introduceNoise(String frame) {
        Random rand = new Random();
        if (rand.nextInt(100) < NOISE_PROBABILITY) {
            // Simulate noise by corrupting the frame
            char[] frameArray = frame.toCharArray();
            int randomIndex = rand.nextInt(frame.length());
            frameArray[randomIndex] = (char) (rand.nextInt(26) + 'a'); // Randomly change a character
            return new String(frameArray);
        }
        return frame;
}
}
