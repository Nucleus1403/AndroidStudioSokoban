using System;
using System.Linq;
using System.Net;
using System.Threading;
using System.Threading.Tasks;
using ServerExemplu;

namespace ServerSokoban
{
    class Program
    {
        private static bool _exitKeyPressed = false;
        static void Main(string[] args)
        {
            Console.Clear();
            ServerCore sv = new ServerCore();
            try
            {
                sv.createServer(9999);   // Port
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }

            // Set-up the event handler to stop the server
            Console.CancelKeyPress += new ConsoleCancelEventHandler(keyHandler);
            string hostName = Dns.GetHostName();
            string myIP = Dns.GetHostByName(hostName).AddressList[0].ToString();
            Console.WriteLine("Port: " + ServerSocket.Port);
            Console.WriteLine("IP: " + myIP);
            Console.WriteLine("Server is running!");
            Console.WriteLine("Press CTRL+C to stop the server!");

            while (!_exitKeyPressed)
            {
                Thread.Sleep(100);
            }

            Console.WriteLine("Running cleanup ...");
            Console.WriteLine("Stopping server ...");
            sv.stopServer();
            Console.WriteLine("Cleaning up clients ...");
            ClientDataStore.instance.stopClients();
            Console.WriteLine("Server is down!");
        }

        public static string load(int id)
        {
            var dataBase = new SokobanLevels.MapsDataBase();
            var queryable = from user in dataBase.Maps where user.Id.Equals(id)
                            select new
                            {
                                user.Id,
                                user.Level
                            };

            foreach (var user in queryable)
            {
                return user.Level;
            }

            return null;
        }

        private static void addMap()
        {
            var dataBase = new SokobanLevels.MapsDataBase();
            var entity = new SokobanLevels.Map
            {
                Id = 3,
                Level = "1100016011004666100401561011410100040040000100001111113011111111"
            };
            dataBase.Maps.Add(entity);
            dataBase.SaveChanges();

        }
        //1110001116340011111046121611401110106011140544611000600111121111
        //1100016011004666100401561011410100040040000100001111113011111111

        protected static void keyHandler(object sender, ConsoleCancelEventArgs args)
        {
            Console.WriteLine("The server was interrupted!");
            args.Cancel = true;
            if (args.SpecialKey == ConsoleSpecialKey.ControlC)
                _exitKeyPressed = true;
        }
    }
}
