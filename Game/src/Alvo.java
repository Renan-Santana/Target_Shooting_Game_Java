public class Alvo {

    // Atributos do objeto Alvo
    private static int vida = 3;
    private int posx, posy;
    private char cor;

    // Metodo Construtor
    public Alvo(int posx, int posy, char cor) {
        this.posx = posx;
        this.posy = posy;
        this.cor = cor;
    }

    // Metodos GET SET
    public static int getVida() {
        return vida;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public char getCor() {
        return cor;
    }

    public void setCor(char cor) {
        this.cor = cor;
    }

    /*
     * Metodo para verificar a cor do alvo, pois dependento da cor é incrementado a
     * vida, ou descrementado ou não nada é feito.
     */
    public char Atira(int posicaoX, int posicaoY) {
        if (posicaoX == this.posx && posicaoY == this.posy) {
            if (this.cor == 'B') {
                Alvo.vida++;
                return 'B';
            } else {
                Alvo.vida--;
                return 'P';
            }
        }
        return 'N';
    }

    /*
     * Metodo para escrever os atributos do objeto
     */
    @Override
    public String toString() {
        return "Alvo [cor=" + cor + ", posx=" + posx + ", posy=" + posy + "]";
    }

}
