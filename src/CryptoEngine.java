import java.util.Random;

public class CryptoEngine {
    private byte[] privKey = new byte[32];
    private byte[] pubKey = new byte[32];
    
    public CryptoEngine() {}

    public void generateKeyPair() {
        Random r = new Random();
        r.nextBytes(privKey);
        privKey[0] &= 248;
        privKey[31] &= 127;
        privKey[31] |= 64;
        
        // Scalar Mult Placeholder
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    private int rotr(int x, int n) {
        return (x >>> n) | (x << (32 - n));
    }

    private int s0(int x) {
        return rotr(x, 2) ^ rotr(x, 13) ^ rotr(x, 22);
    }

    private int s1(int x) {
        return rotr(x, 6) ^ rotr(x, 11) ^ rotr(x, 25);
    }

    private int ch(int x, int y, int z) {
        return (x & y) ^ (~x & z);
    }

    private int maj(int x, int y, int z) {
        return (x & y) ^ (x & z) ^ (y & z);
    }
}