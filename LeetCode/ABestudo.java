import java.util.LinkedList;
import java.util.Queue;

class No {
    int elemento;
    public No esq;
    public No dir;

    public No(int elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }

    public No(int elemento, No dir, No esq) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria {
    public No raiz;

    ArvoreBinaria() {
        this.raiz = null;
    }

    void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    No inserir(int x, No i) throws Exception {
        // tem que chamar recursivamente
        if (i == null) {
            i = new No(x);
        } else if (x < i.elemento) { // se for menor esquerda
            i.esq = inserir(x, i.esq);
        } else if (x > i.elemento) { // se for maior direita
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro!");
        }
        return i;
    }

    public void inserirPai(int x) throws Exception {
        if (raiz == null) {
            raiz = new No(x); // cria primeiro
        } else if (x < raiz.elemento) {
            inserirPai(x, raiz.esq, raiz); // insere na esq
        } else if (x > raiz.elemento) {
            inserirPai(x, raiz.dir, raiz); // insere na dir
        } else {
            throw new Exception("Erro!");
        }
    }

    private void inserirPai(int x, No i, No pai) throws Exception {
        if (i == null) { // vão ser os primeiros filhos
            if (x < pai.elemento) {
                pai.esq = new No(x);
            } else {
                pai.dir = new No(x);
            }
        } else if (x < i.elemento) { // inserir nos filhos
            inserirPai(x, i.esq, i);
        } else if (x > i.elemento) {
            inserirPai(x, i.dir, i);
        } else {
            throw new Exception("Erro!");
        }
    }

    void ordenar() {
        ordenar(raiz);
    }

    void ordenar(No i) {
        if (i != null) {
            ordenar(i.esq);
            System.out.print(i.elemento + " ");
            ordenar(i.dir);
        }
    }

    void remover(int x) throws Exception {
        raiz = remover(x, raiz);
    }

    No remover(int x, No i) throws Exception {
        if (i == null) {
            throw new Exception("Erro!");
        } else if (x < i.elemento) {
            i.esq = remover(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = remover(x, i.dir);
        } else if (i.dir == null) {
            i = i.esq;
        } else if (i.esq == null) {
            i = i.dir;
        } else {
            i.esq = maiorEsq(i, i.esq);
        }
        return i;
    }

    No maiorEsq(No i, No j) throws Exception {
        if (j.dir == null) {
            i.elemento = j.elemento;
            j = j.dir;
        } else {
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }

    // ve se as arvores são iguais
    public static boolean iguais(ArvoreBinaria i, ArvoreBinaria j) { // manda duas arvores
        return iguais(i.raiz, j.raiz); // manda as raizes delas
    }

    public static boolean iguais(No i, No j) { // vai indo pelos nos
        boolean resp = false;
        if (i != null && j != null) {
            resp = (i.elemento == j.elemento && iguais(i.dir, j.dir) && iguais(i.esq, j.esq));
        } else if (i == null && j == null) {
            resp = true;
        }

        return resp;
    }

    // ve se é simetrico
    public boolean isSymmetric(ArvoreBinaria i) {
        return isSymmetric(i.raiz);
    }

    public static boolean isSymmetric(No raiz) {
        return (isMirror(raiz, raiz)); // "cria" dias arvores da mesma arvore
    }

    public static boolean isMirror(No r1, No r2) {
        if (r1 == null && r2 == null)
            return true;
        if (r1 == null || r2 == null)
            return false;

        return (r1.elemento == r2.elemento) && isMirror(r1.esq, r2.dir) && isMirror(r1.dir, r2.esq);
        // compara os lados diferentes das duas arvores
    }

    // inverter arvore
    public No invertTree(ArvoreBinaria i) {
        return invertTree(i.raiz);
    }

    public static No invertTree(No raiz) {
        if (raiz == null) {
            return raiz;
        }
        No left = invertTree(raiz.esq);
        No right = invertTree(raiz.dir);

        // swap
        raiz.dir = left;
        raiz.esq = right;

        return raiz;
        /*
         * ou
         * if (riaz == null) return raiz;
         * 
         * -> SWAP
         * No tmp = raiz.esq;
         * raiz.esq = invertTree(raiz.dir);
         * raiz.dir = invertTree(tmp);
         * 
         * return raiz;
         */
    }

    // maior altura
    public int maxDepth(ArvoreBinaria i) {
        return maxDepth(i.raiz);
    }

    public int maxDepth(No raiz) {
        return getAltura(raiz, 0);
    }

    public int getAltura(No raiz, int altura) {
        if (raiz == null) {
            return altura;
        } else {
            int altDir = getAltura(raiz.dir, altura + 1);
            int altEsq = getAltura(raiz.esq, altura + 1);
            if (altDir > altEsq) {
                altura = altDir;
            } else {
                altura = altEsq;
            }
        }
        return altura;
    }

    // mostra por nível
    void mostrarNivel() {
        if (raiz != null) {
            Queue<No> fila = new LinkedList<>();
            fila.add(raiz);

            while (!fila.isEmpty()) {
                No tempNode = fila.poll();
                System.out.print(tempNode.elemento + " ");

                if (tempNode.esq != null)
                    fila.add(tempNode.esq);
                if (tempNode.dir != null)
                    fila.add(tempNode.dir);
            }
        }
    }

    // se a arvore esta balanceada ou não (TEM QEU OLHAR AS ALTURAS)
    public boolean isBalanced(ArvoreBinaria i) {
        return isBalanced(i.raiz);
    }

    public boolean isBalanced(No raiz) {
        if (raiz == null)
            return true;
        if (Math.abs(getAltura(raiz.esq) - getAltura(raiz.dir)) > 1)
            return false;
        // manda calcular altura da dir e da esq e ve se é maior ou menor qeu um
        return isBalanced(raiz.esq) && isBalanced(raiz.dir); // vai repetindo
    }

    public int getAltura(No raiz) {
        if (raiz == null)
            return 0;
        return 1 + Math.max(getAltura(raiz.esq), getAltura(raiz.dir)); // mesma coisa do get altura original
    }

    // ve a menor altura;
    public int minDepth(ArvoreBinaria i) {
        return minDepth(i.raiz);
    }

    public int minDepth(No raiz) {
        if (raiz == null) {
            return 0;
        } else {
            int altDir = minDepth(raiz.dir);
            int altEsq = minDepth(raiz.esq);
            if (altDir == 0 || altEsq == 0) {
                // Se uma das subárvores estiver vazia, retorne a profundidade máxima da
                // subárvore não vazia mais 1
                return Math.max(altDir, altEsq) + 1;
            } else {
                // Se ambas as subárvores não estiverem vazias, retorne a profundidade mínima
                // das duas subárvores mais 1
                return Math.min(altDir, altEsq) + 1;
            }
        }
    }

    // ve se no cainho tem a soma dada
    public boolean hasPathSum(ArvoreBinaria i, int targetSum) {
        return hasPathSum(i.raiz, targetSum);
    }

    public boolean hasPathSum(No raiz, int targetSum) {
        return temSoma(raiz, 0, targetSum);
    }

    public boolean temSoma(No raiz, int soma, int targetSum) {
        if (raiz == null)
            return false;
        soma += raiz.elemento;
        if (raiz.esq == null && raiz.dir == null && soma == targetSum) { // caso base
            return true;
        } else if (temSoma(raiz.esq, soma, targetSum)) { // ve esquerda
            return true;
        } else if (temSoma(raiz.dir, soma, targetSum)) { // ve direita
            return true;
        }
        return false;
    }
}

public class ABestudo {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria ab = new ArvoreBinaria();
        ab.inserir(3);
        ab.inserir(5);
        ab.inserir(1);
        ab.inserir(8);
        ab.inserir(2);
        ab.inserir(4);
        ab.inserir(7);
        ab.inserir(6);
        ab.ordenar();
        System.out.println("");
        ArvoreBinaria ab2 = new ArvoreBinaria();
        ab2.inserirPai(3);
        ab2.inserirPai(5);
        ab2.inserirPai(1);
        ab2.inserirPai(8);
        ab2.inserirPai(6);
        ab2.inserirPai(7);
        ab2.remover(5);
        ab2.ordenar();
        System.out.println("");
        ArvoreBinaria ab3 = new ArvoreBinaria();
        ab3.inserir(3);
        ab3.inserir(5);
        ab3.inserir(1);
        ab3.inserir(8);
        ab3.inserir(2);
        ab3.inserir(4);
        ab3.inserir(7);
        ab3.inserir(6);
        ab3.ordenar();
        System.out.println("");
        ArvoreBinaria ab4 = new ArvoreBinaria();
        ab4.inserir(3);
        ab4.inserir(5);
        ab4.inserir(1);
        ab4.inserir(8);
        ab4.inserir(2);
        ab4.inserir(4);
        ab4.inserir(7);
        ab4.inserir(6);
        ab4.ordenar();
        System.out.println("");
        boolean resposta = ArvoreBinaria.iguais(ab3, ab4);
        ab4.invertTree(ab4);
        ab4.ordenar();
        ab4.isSymmetric(ab4);
        ab4.maxDepth(ab4);
        System.out.println(resposta);
    }
}
