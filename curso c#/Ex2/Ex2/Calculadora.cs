using System;
using System.Linq;
using System.Threading.Tasks;

namespace Ex2 {
    internal class Calculadora {

        public double Pi = 3.14;

        public double Circunferencia(double r) {
            return 2.0 * Pi * r;
        }

        public double Volume(double r) {
            return 4.0 / 3.0 * Pi * Math.Pow(r, 3);
        }
    }
}
