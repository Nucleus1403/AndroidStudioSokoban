using System;
using System.Text;
using System.Net.Sockets;
using System.Threading;

namespace ServerSokoban
{
    class ClientHandler
    {
        private Socket _sk = null;
        private int _idx = -1;
        private Thread _th = null;
        private bool _shouldRun = true;
        private bool _isRunning = true;
        public ClientHandler(Socket sk, int id)
        {
            _sk = sk;
            _idx = id;
        }

        public void initClient()
        {
            if (null != _th)
                return;

            _th = new Thread(new ThreadStart(run));
            _th.Start();
        }

        public void stopClient()
        {
            if (_th == null )
                return;

            _sk.Close();
            _shouldRun = false;
        }

        public bool SocketConnected(Socket s)
        {
            bool part1 = s.Poll(10000, SelectMode.SelectRead);
            bool part2 = (s.Available == 0);
            if (part1 && part2)
                return false;
            else
                return true;
        }

        // android -> server
        private void handleMsg(String message)
        {
            String backmsg = "";
            String decodedmsg;
            decodedmsg = message;//.Split('#');

            if (decodedmsg[0].Equals('1'))
            {
                backmsg = Program.load(1)+ '#';
            }
            else if (decodedmsg[0].Equals('2'))
            {
                backmsg = Program.load(2) + '#';
            }
            else if (decodedmsg[0].Equals('3'))
            {
                backmsg = Program.load(1) + '#';
            }

            Thread.Sleep(1);
            sendResponse(backmsg);
        }

        // server -> android
        private void sendResponse(String msgrsp)
        {
            byte[] bytesMsgRaspuns = Encoding.ASCII.GetBytes(msgrsp);
            _sk.Send(bytesMsgRaspuns);
        }
        
        private void run()
        {
            while (_shouldRun)
            {
                byte[] rawMsg = new byte[1024];
                try
                {
                    int bCount = _sk.Receive(rawMsg);
                    String msg = Encoding.UTF8.GetString(rawMsg);

                    if (bCount > 0)
                    {
                        Console.WriteLine("Client " + ": " + msg);    // "Client " + _idx + ": " + msg 
                        handleMsg(msg);
                    }
                }
                catch (Exception ex)
                {
                    return;
                }
                Thread.Sleep(1);
            }
            _isRunning = false;
        }

        public bool isAlive()
        {
            return _isRunning;
        }
    }
}
