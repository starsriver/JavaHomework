using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using PIMBackend.Model;

namespace PIMBackend.Controllers
{

    [Route("api/")]
    public class MainController : Controller
    {
        private PIMContext context;
        public MainController(PIMContext context)
        {
            this.context = context;
        }
        // Get http://hostname:port/api/get/{option}
        [HttpGet,Route("get/{option}")]
        public JsonResult Get(string option)
        {
            JsonResult jr = null;
            switch(option.ToLower()){
                case "all":
                {
                    var result1 = context.PIMTodos.Select(item => item.ToString());
                    var result2 = context.PIMNotes.Select(item => item.ToString());
                    var result3 = context.PIMContacts.Select(item => item.ToString());
                    var result4 = context.PIMAppointments.Select(item => item.ToString());
                    var result = result1.Concat(result2).Concat(result3).Concat(result4);
                    jr = new JsonResult(result);
                }
                break;
                case "todos":
                {
                    var result = context.PIMTodos.Select(item => item.ToString());
                    jr = new JsonResult(result);
                }
                break;
                case "notes":
                {
                    var result = context.PIMNotes.Select(item => item.ToString());
                    jr = new JsonResult(result);
                }
                break;
                case "contacts":
                {
                    var result = context.PIMContacts.Select(item => item.ToString());
                    //result.Contains
                    jr = new JsonResult(result);
                }
                break;
                case "appointments":
                {
                    var result = context.PIMAppointments.Select(item => item.ToString());
                    jr = new JsonResult(result);
                }
                break;
                default:{}
                break;
            }
            return jr;
        }
        // Post http://hostname:port/api/save
        [HttpPost,Route("save")]
        public async Task<int> Post([FromBody]HashSet<String> value)
        {
            AddDateAsync(value);
            return await context.SaveChangesAsync();
        }

        private async void AddDateAsync(HashSet<String> values)
        {
            List<string> PIMTodos = new List<string>();
            List<string> PIMNotes = new List<string>();
            List<string> PIMAppointments = new List<string>();
            List<string> PIMContacts = new List<string>();
            var OldPIMTodos = context.PIMTodos.Select(item => item.ToString());
            var OldPIMNotes = context.PIMNotes.Select(item => item.ToString());
            var OldPIMAppointments = context.PIMAppointments.Select(item => item.ToString());
            var OldPIMContacts = context.PIMContacts.Select(item => item.ToString());
            foreach(string item in values)
            {
                if(item.Contains("Type: PIMTodo"))
                {
                    PIMTodos.Add(item);
                    continue;
                }
                else if(item.Contains("Type: PIMNote"))
                {
                    PIMNotes.Add(item);
                    continue;
                }
                else if(item.Contains("Type: PIMAppo"))
                {
                    PIMAppointments.Add(item);
                    continue;
                }
                else if(item.Contains("Type: PIMCont"))
                {
                    PIMContacts.Add(item);
                    continue;
                }
                else
                {
                    continue;
                }
            }
            var newPIMTodos = PIMTodos.Except(OldPIMTodos);
            foreach(string item in newPIMTodos)
            {
                await context.PIMTodos.AddAsync(PIMTodo.FromString(item));
            }

            var newPIMNotes = PIMNotes.Except(OldPIMNotes);
            foreach(string item in newPIMNotes)
            {
               await context.PIMNotes.AddAsync(PIMNote.FromString(item));
            }

            var newPIMAppointments = PIMAppointments.Except(OldPIMAppointments);
            foreach(string item in newPIMAppointments)
            {
                await context.PIMAppointments.AddAsync(PIMAppointment.FromString(item));
            }

            var newPIMContacts = PIMContacts.Except(OldPIMContacts);
            foreach(string item in newPIMContacts)
            {
                await context.PIMContacts.AddAsync(PIMContact.FromString(item));
            }
        }
    }
}
