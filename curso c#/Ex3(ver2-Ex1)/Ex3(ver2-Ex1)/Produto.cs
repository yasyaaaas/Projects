using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection.Metadata;
using System.Text;
using System.Threading.Tasks;

namespace Ex3_ver2_Ex1_ {
    internal class Produto {
        private string _nome;
        public double Preco { get; private set; }
        public int Quantidade { get; private set; }

        public Produto() {
        }

        public Produto(string nome, double preco, int quantidade) {
            _nome = nome;
            Preco = preco;
            Quantidade = quantidade;
        }

        public Produto(string nome, double preco) {
            _nome = nome;
            Preco = preco;
            Quantidade = 0;
        }

        public string Nome {
            get { return _nome; }
            set {
                if(value != null && value.Length > 1) {
                    _nome = value;
                }
            }
        }

        public double ValorTotalEmEstoque() {
            return Preco * Quantidade;
        }

        public void AdicionarProdutos(int quantidade) {
            this.Quantidade += quantidade;
        }

        public void RemoverProdutos(int quantidade) {
            this.Quantidade -= quantidade;
        }

        public override string ToString() {
            return _nome + ", $ " + Preco.ToString("F2") + ", " + Quantidade + " unidades, Total: $ " + ValorTotalEmEstoque().ToString("F2");
        }
    }
}
