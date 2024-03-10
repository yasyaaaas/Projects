using MeuDiario.API.Models;
using Microsoft.AspNetCore.Mvc;

namespace MeuDiario.API.Controllers {

    [ApiController]
    [Route("api/[controller]")]
    public class DiarioControllers : ControllerBase {
        private static List<Diario> _diario = new List<Diario>();
        private static int nextId = 1;

        [HttpPost] // colocar na lista
        public IActionResult Post(Diario diario) {
            diario.Id = nextId++;
            _diario.Add(diario);
            return Ok(diario);
        }

        [HttpGet] // mostrar lista
        public IActionResult GetAll() {
            return Ok(_diario);
        }

        [HttpGet("{id}")] // pega a lista por id
        public IActionResult Get(int id) {
            var diario = _diario.FirstOrDefault(diario => diario.Id == id);
            return Ok(diario);
        }

        [HttpDelete] // deleta algum da lista
        public IActionResult Delete(int id) {
            var diario = _diario.FirstOrDefault(diario => diario.Id == id);
            if (diario == null) {
                return NotFound();
            }
            _diario.Remove(diario);
            return Ok(_diario);
        }
    }
}
