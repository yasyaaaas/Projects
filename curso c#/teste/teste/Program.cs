using System;


namespace teste {
    internal class Program {
        static void Main(string[] args) {
            Triangulo x, y;
            x = new Triangulo();
            y = new Triangulo();

            Console.WriteLine("Entre com os lados do triangulo x: ");
            x.A = double.Parse(Console.ReadLine());
            x.B = double.Parse(Console.ReadLine());
            x.C = double.Parse(Console.ReadLine());
            Console.WriteLine("Entre com os lados do triangulo y: ");
            y.A = double.Parse(Console.ReadLine());
            y.B = double.Parse(Console.ReadLine());
            y.C = double.Parse(Console.ReadLine());

            double areaX = x.Area();
            double areaY = y.Area();
            
            Console.WriteLine("Area de X: " + areaX);
            Console.WriteLine("Area de Y: " + areaY);
            char maiorArea = ' ';
            if (areaX > areaY) {
                maiorArea = 'X';
            } else {
                maiorArea = 'Y';
            }
            Console.WriteLine("Maior area: " + maiorArea);
        }
    }
}
