import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ClasseJava {
    // cria classe de Jogador
    public class Jogador {
        private int id;
        private String nome;
        private int altura;
        private int peso;
        private String universidade;
        private int anoNascimento;
        private String cidadeNascimento;
        private String estadoNascimento;

        public Jogador() {

        }

        // construtor com atributos
        public Jogador(int id, String nome, int altura, int peso, String universidade,
                int anoNascimento, String cidadeNascimento, String estadoNascimento) {
            this.id = id;
            this.nome = nome;
            this.altura = altura;
            this.peso = peso;
            this.universidade = universidade;
            this.anoNascimento = anoNascimento;
            this.cidadeNascimento = cidadeNascimento;
            this.estadoNascimento = estadoNascimento;
        }

        // set e gets
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getAltura() {
            return altura;
        }

        public void setAltura(int altura) {
            this.altura = altura;
        }

        public int getPeso() {
            return peso;
        }

        public void setPeso(int peso) {
            this.peso = peso;
        }

        public String getUniversidade() {
            return universidade;
        }

        public void setUniversidade(String universidade) {
            this.universidade = universidade;
        }

        public int getAnoNascimento() {
            return anoNascimento;
        }

        public void setAnoNascimento(int anoNascimento) {
            this.anoNascimento = anoNascimento;
        }

        public String getCidadeNascimento() {
            return cidadeNascimento;
        }

        public void setCidadeNascimento(String cidadeNascimento) {
            this.cidadeNascimento = cidadeNascimento;
        }

        public String getEstadoNascimento() {
            return estadoNascimento;
        }

        public void setEstadoNascimento(String estadoNascimento) {
            this.estadoNascimento = estadoNascimento;
        }

        public Jogador clone() {
            return new Jogador(id, nome, altura, peso, universidade, anoNascimento,
                    cidadeNascimento, estadoNascimento);
        }

        // imprime
        public void imprimir() {
            System.out.println(
                    "[" + id + " ## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## "
                            + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + "]");
        }

        // le o arquivo
        public void ler(String novoID) { // pega o id colocado no input
            try {
                BufferedReader reader = new BufferedReader(new FileReader("/tmp/players.csv"));
                // le arquivo
                String info;
                while ((info = reader.readLine()) != null) {
                    // info vai ser a linha atual do arquivo
                    // enquqnto a linha não for vazia
                    String[] campos = info.split(",", -1); // separa em strings depois da virgula
                    // -1 -> serve para que mesmo as strings vazias sejam contadas
                    if (campos.length == 8 && campos[0].equals(novoID.trim())) {
                        // ve se os campos estão tooas lá
                        // ve se o 1° campo (id do jogador) é igual a id que foi colocada
                        id = Integer.parseInt(campos[0]); // id passa a ter o valor do campo[0] em int
                        nome = campos[1];
                        altura = Integer.parseInt(campos[2]);
                        peso = Integer.parseInt(campos[3]);
                        universidade = campos[4];
                        anoNascimento = Integer.parseInt(campos[5]);
                        cidadeNascimento = campos[6];
                        estadoNascimento = campos[7];
                        break;// Sai do loop quando encontra o jogador do id
                    }
                }
                reader.close(); // fecha arquivo
            } catch (

            IOException e) {
                e.printStackTrace();
            }
            // atribui "nao informado no que estiver vazio"
            if (nome == null || nome.isEmpty()) {
                nome = "nao informado";
            }
            if (universidade == null || universidade.isEmpty()) {
                universidade = "nao informado";
            }
            if (cidadeNascimento == null || cidadeNascimento.isEmpty()) {
                cidadeNascimento = "nao informado";
            }
            if (estadoNascimento == null || estadoNascimento.isEmpty()) {
                estadoNascimento = "nao informado";
            }
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String novoId;
        while (true) {
            novoId = scanner.nextLine(); // le o id do input
            if (novoId.charAt(0) == 'F' && novoId.charAt(1) == 'I' && novoId.charAt(2) == 'M') {
                break; // vai ate fim
            }
            Jogador jogador = new ClasseJava().new Jogador(); // cria um Jogador
            jogador.ler(novoId); // le as info do jogador
            jogador.imprimir(); // imprime o jogador
        }
        scanner.close();
    }
}