using System;


namespace Ex2 {
    internal class Principal {

        static void Main(string[] args) {

            Calculadora calc = new Calculadora();
            Console.Write("Entre com o valor do raio: ");
            double raio = double.Parse(Console.ReadLine());
            double circ = calc.Circunferencia(raio);

            Console.WriteLine("Circunferência: " + circ);
            Console.WriteLine();
            Console.WriteLine("Volume: " + calc.Volume(raio));
            Console.WriteLine();
            Console.WriteLine("Valor de Pi: " + calc.Pi.ToString("F2"));
        }

       
    }
}
