using System.Collections.Generic;

namespace ServerSokoban
{
    class ClientDataStore
    {
        private List<ClientHandler> _cl = new List<ClientHandler>();
        private static ClientDataStore _instance = new ClientDataStore();

        public static ClientDataStore instance
        {
            get
            {
                return _instance;
            }
        }

        public void addClient(ClientHandler cl)
        {
            lock (_cl)
            {
                _cl.Add(cl);
            }
        }

        public void stopClients()
        {
            lock (_cl)
            {
                foreach (ClientHandler cl in _cl)
                    cl.stopClient();
            }
        }

        public int clientCount
        {
            get
            {
                return _cl.Count;
            }
        }
    }

    // EOF - End Of File
}
