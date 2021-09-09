import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {

        ArrayList<Alvo> alvos = new ArrayList<Alvo>(15);

        criarAlvos(alvos);

        exibirAlvos(alvos);

        start(alvos);

    }

    /* Metodo para criar os alvos em posicoes aleatoria e diferentes */
    private static void criarAlvos(ArrayList<Alvo> alvos) {
        Random random = new Random();
        Alvo alvo;
        int cont = 15;
        while (cont != 0) {
            int x = random.nextInt(5) + 1;
            int y = random.nextInt(5) + 1;
            if (verificarPosicoes(x, y, alvos)) {
                if (cont <= 3) {
                    alvo = new Alvo(x, y, 'B');
                    alvos.add(alvo);
                    cont--;
                } else {
                    alvo = new Alvo(x, y, 'P');
                    alvos.add(alvo);
                    cont--;
                }

            }
        }
    }

    /*
     * Metodo que retorna 'false' se existir algum alvo com as mesmas posicoes, ou
     * 'true' se nao tem nenhum alvo com as coordenadas passadas como parametros
     */
    private static boolean verificarPosicoes(int x, int y, ArrayList<Alvo> alvos) {
        for (Alvo alvo : alvos) {
            if (alvo.getPosx() == x && alvo.getPosy() == y) {
                return false;
            }
        }
        return true;
    }

    /*
     * Metodo que retorna 'true' se existir o alvo com as coordenadas passadas como
     * parametros, ou 'false' caso não exista
     */
    private static boolean pesquisaAlvo(int x, int y, ArrayList<Alvo> alvos) {
        for (Alvo alvo : alvos) {
            if (alvo.getPosx() == x && alvo.getPosy() == y) {
                return true;
            }
        }
        return false;
    }

    /*
     * Metodo que retorna a posicao das coordenados do alvo passadas como paramentro
     */
    private static int retornaPosicaoAlvo(int x, int y, ArrayList<Alvo> alvos) {
        for (Alvo alvo : alvos) {
            if (alvo.getPosx() == x && alvo.getPosy() == y) {
                return alvos.indexOf(alvo);
            }
        }
        return -1;
    }

    /* Metodo para mostrar todos os alvos no ArrayList */
    private static void exibirAlvos(ArrayList<Alvo> alvos) {
        String aux = "";
        for (Alvo alvo : alvos) {
            aux += alvo.toString() + "\n";
        }
        System.out.println(aux);
    }

    /* Metodo para iniciar o jogo */
    private static void start(ArrayList<Alvo> alvos) {

        Random random = new Random();
        Scanner scan = new Scanner(System.in);
        int qtdTiros = 10;

        while (qtdTiros != 0 && Alvo.getVida() != 0) {

            System.out.print("\n\nInsira a posicao 'X' do seu tiro com um número inteiro(de '1' à '5'): ");
            int tiroX = scan.nextInt();

            System.out.print("Insira a posicao 'Y' do seu tiro om um número inteiro(de '1' à '5'): ");
            int tiroY = scan.nextInt();

            if (!pesquisaAlvo(tiroX, tiroY, alvos)) {
                System.err.println("\nErrou!\n");
                qtdTiros--;
                exibirAlvos(alvos);
                System.out.println("\n\tQuantidade de alvos restante: " + alvos.size());
                System.out.println("\tQuantidade de tiros restante: " + qtdTiros);
                System.out.println("\tQuantidade de vidas restante: " + Alvo.getVida());
            } else {
                int posicaoAlvoAcertado = retornaPosicaoAlvo(tiroX, tiroY, alvos);
                Alvo aux = alvos.get(posicaoAlvoAcertado);
                if (aux.Atira(tiroX, tiroY) == 'P') {

                    System.err.println("\nAcertou um alvo preto!!!\n");
                    int x, y;

                    do {
                        x = random.nextInt(5) + 1;
                        y = random.nextInt(5) + 1;
                    } while (!verificarPosicoes(x, y, alvos));

                    Alvo novoAlvo = new Alvo(x, y, 'P');
                    alvos.set(posicaoAlvoAcertado, novoAlvo);
                    qtdTiros--;
                    exibirAlvos(alvos);
                    System.out.println("\n\tQuantidade de alvos restante: " + alvos.size());
                    System.out.println("\tQuantidade de tiros restante: " + qtdTiros);
                    System.out.println("\tQuantidade de vidas restante: " + Alvo.getVida());

                } else if (aux.Atira(tiroX, tiroY) == 'B') {
                    System.err.println("\nAcertou um alvo branco\n");
                    alvos.remove(posicaoAlvoAcertado);
                    qtdTiros--;
                    exibirAlvos(alvos);
                    System.out.println("\n\tQuantidade de alvos restante: " + alvos.size());
                    System.out.println("\tQuantidade de tiros restante: " + qtdTiros);
                    System.out.println("\tQuantidade de vidas restante: " + Alvo.getVida());
                }
            }
        }

        if (qtdTiros == 0) {

            System.out.println("\n\n\t****** GAME OVER! ******");
        } else {

            System.out.println("\n\n\t****** VENCEU! ******");
        }
    }

}
