using System;
using System.Runtime.CompilerServices;

namespace Matriz {
    internal class Program {
        static void Main(string[] args) {
            int n = int.Parse(Console.ReadLine());
            int[,] mat = new int[n, n];
            for(int i = 0; i < n; i++) {
                string[] values = Console.ReadLine().Split(' '); // vai ler a linha de numeros e separar por espaço
                for(int j = 0; j < n; j++) {
                    mat[i,j] = int.Parse(values[j]); // vai pegar os valores e converter para inteiros e colocar na matriz
                }
            }
            Console.WriteLine("Main diagonal: ");
            for(int i = 0; i < n; i++) {
                Console.Write(mat[i,i] + " "); // vai imprimir a diagonal principal
            }
            Console.WriteLine();
            int count = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if (mat[i,j] < 0) {
                        count++; // vai contar quantos numeros negativos tem na matriz
                    }
                }
            }
            Console.WriteLine("Negative numbers: " + count);
        }
    }
}
