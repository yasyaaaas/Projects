using System;

namespace Ex3_ver2_Ex1_ {
    internal class Principal {
        static void Main(string[] args) {
           Produto p = new Produto("TV", 500.00, 10);
            p.Nome = "T";

            Console.WriteLine(p.Nome);
            Console.WriteLine(p.Preco);
        }
    }
}
