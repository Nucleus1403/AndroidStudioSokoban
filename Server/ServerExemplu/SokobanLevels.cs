using System.ComponentModel.DataAnnotations;
using System.Data.Entity;

namespace ServerExemplu
{
    internal class SokobanLevels
    {
        public class Map
        {
            [Key]
            public int Id { get; set; }
            public string Level { get; set; }
        }

        public class MapsDataBase : DbContext
        {
            public DbSet<Map> Maps { get; set; }
        }
    }
}
