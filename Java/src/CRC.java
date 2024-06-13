import java.io.*;

class CRC {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Generator:");
        String gen = br.readLine();
        System.out.println("Enter Data:");
        String data = br.readLine();
        String code = data;

        // Append zeros to the data
        while (code.length() < (data.length() + gen.length() - 1))
            code += "0";
        
        // Perform division and get the remainder
        code = data + div(code, gen);
        
        System.out.println("The transmitted Code Word is: " + code);
        System.out.println("Please enter the received Code Word: ");
        String rec = br.readLine();
        
        // Check if the received code word is error-free
        if (Integer.parseInt(div(rec, gen)) == 0)
            System.out.println("The received code word contains no errors.");
        else
            System.out.println("The received code word contains errors.");
    }

    static String div(String num1, String num2) {
        int pointer = num2.length();
        String result = num1.substring(0, pointer);
        String remainder = "";

        // Perform XOR division
        for (int i = 0; i < num2.length(); i++) {
            if (result.charAt(i) == num2.charAt(i))
                remainder += "0";
            else
                remainder += "1";
        }

        while (pointer < num1.length()) {
            if (remainder.charAt(0) == '0') {
                remainder = remainder.substring(1);
                remainder += num1.charAt(pointer);
                pointer++;
            }
            result = remainder;
            remainder = "";
            for (int i = 0; i < num2.length(); i++) {
                if (i < result.length()) {
                    if (result.charAt(i) == num2.charAt(i))
                        remainder += "0";
                    else
                        remainder += "1";
                } else {
                    remainder += "0";
                }
            }
        }

        return remainder.substring(1);
    }
}
