import java.math.BigInteger;
import java.security.SecureRandom;


// SecureRandom generates random numbers that are suitable for cryptographic use, ensuring they are difficult to predict or replicate.

// This makes it resistant to attacks that exploit patterns or predictability in random number generation.
public class RSAExample {

    // Function to generate keys
    public static BigInteger[] generateKeys(int bitLength) {
        SecureRandom random = new SecureRandom();

        // Generate two large prime numbers
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        BigInteger q = BigInteger.probablePrime(bitLength, random);

        // Calculate n = p * q
        BigInteger n = p.multiply(q);

        // Calculate phi(n) = (p-1)*(q-1)
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Choose an encryption key e such that 1 < e < phi(n) and gcd(e, phi) = 1
        BigInteger e;
        do {
            e = new BigInteger(bitLength / 2, random);
        } while (!e.gcd(phi).equals(BigInteger.ONE) || e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(phi) >= 0);

        // Calculate the decryption key d such that e * d mod phi = 1
        BigInteger d = e.modInverse(phi);

        // Return keys as an array: [e, d, n]
        return new BigInteger[]{e, d, n};
    }

    // Function to encrypt a message
    public static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
        return message.modPow(e, n);
    }

    // Function to decrypt a message
    public static BigInteger decrypt(BigInteger cipherText, BigInteger d, BigInteger n) {
        return cipherText.modPow(d, n);
    }

    public static void main(String[] args) {
        int bitLength = 1024; // Key size

        // Generate RSA keys
        BigInteger[] keys = generateKeys(bitLength);
        BigInteger e = keys[0]; // Public key exponent
        BigInteger d = keys[1]; // Private key exponent
        BigInteger n = keys[2]; // Modulus

        System.out.println("Public Key (e, n): " + e + ", " + n);
        System.out.println("Private Key (d, n): " + d + ", " + n);

        // Example message
        String message = "Hello, RSA!";
        BigInteger messageAsNumber = new BigInteger(message.getBytes());

        // Encrypt the message
        BigInteger cipherText = encrypt(messageAsNumber, e, n);
        System.out.println("Encrypted Message: " + cipherText+"\n");

        // Decrypt the message
        BigInteger decryptedMessage = decrypt(cipherText, d, n);
        System.out.println("Decrypted Message: " + new String(decryptedMessage.toByteArray()));
    }
}