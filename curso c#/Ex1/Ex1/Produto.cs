using System;
using System.Linq;
using System.Threading.Tasks;

namespace Ex1 {
    internal class Produto {
        public string Nome;
        public double Preco;
        public int Quantidade;

        public double valorTotalEmestoque() {
            return Preco * Quantidade;
        }

        public override string ToString() {
            return Nome + ", $" + Preco + ", " + Quantidade + " unidades, Total: $" + valorTotalEmestoque();
        }

        public void AdicionarProsutos(int quantidade) {
            Quantidade += quantidade;
        }

        public void RemoverProdutos(int quantidade) {
            Quantidade -= quantidade;
        }
    }
}
