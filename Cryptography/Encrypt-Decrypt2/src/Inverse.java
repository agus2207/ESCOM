import java.util.ArrayList;

public class Inverse {
    
    public int euclides(int alpha){
        int resp = 0, b = 256;
        while(b != 0){
            resp = alpha%b;
            alpha=b;
            b=resp;
        }
        return alpha;
    } 
    
    public int invaditivo(int beta){
        beta = beta%256;
        int inv = 256-beta;
        return inv;
    }
    
    public int euclidesExtendido(int alpha) {
        int x = 0, y = 0, d = 0, b = 256;
        int x2 = 1, x1 = 0, y2 = 0, y1 = 1;
        int q = 0, r = 0;
        while (b > 0) {
            q = (alpha / b);
            r = alpha - q * b;
            x = x2 - q * x1;
            y = y2 - q * y1;
            alpha = b;
            b = r;
            x2 = x1;
            x1 = x;
            y2 = y1;
            y1 = y;
        }
        if(x2 < 0)
            x2 = 256+x2;
        return x2;
    } 
}
