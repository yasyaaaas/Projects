using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ex4 {
    internal class Conta {
        public int Numero { get; private set; }
        public string Titular { get; set; }
        public double Saldo { get; private set; }

        public Conta(int numero, string titular) {
            Numero = numero;
            Titular = titular;
            Saldo = 0.0;
        }

        public Conta(int numero, string titular, double saldo) : this(numero, titular) {
           Deposito(saldo);
        }

        public void Deposito(double valor) {
            Saldo += valor;
        }

        public void Saque(double valor) {
            Saldo -= valor + 5.0;
        }

        override public string ToString() {
            return $"Conta {Numero}, Titular: {Titular}, Saldo: ${Saldo}";
        }
    }
}
