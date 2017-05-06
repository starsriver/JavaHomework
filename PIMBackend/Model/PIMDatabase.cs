using System;
using System.Text.RegularExpressions;
using Microsoft.EntityFrameworkCore;
namespace PIMBackend.Model
{
    public class PIMTodo
    {
        public PIMTodo(){}
        public PIMTodo(string Priority, string PIMDate, string Contains)
        {
            this.Priority = Priority;
            this.PIMDate = PIMDate;
            this.Contains = Contains;
        }
        public string Priority {get; set;}
        public string PIMDate {get; set;}
        public string Contains {get; set;}
        public override string ToString()
        {
            return String.Format("Type: PIMTodo    Priority: {0}    Date: {1}    {2}",this.Priority, this.PIMDate, this.Contains);
        }
        public static PIMTodo FromString(string str)
        {
            Match mt= Regex.Match(str,@"Type:\s(?<Type>\S+)\s{4}Priority:\s(?<Priority>\S+)\s{4}Date:\s(?<Date>\S+)\s{4}(?<Contains>\S+)");
            if(mt.Success && mt.Groups["Type"].Value =="PIMTodo")
            {
                return new PIMTodo(mt.Groups["Priority"].Value, mt.Groups["Date"].Value, mt.Groups["Contains"].Value);
            }
            else
            {
                return null;
            }
        }
    }
    public class PIMNote
    {
        public PIMNote(){}
        public PIMNote(string Priority, string Contains)
        {
            this.Priority = Priority;
            this.Contains = Contains;
        }
        public string Priority {get; set;}
        public string Contains {get; set;}
        public override string ToString()
        {
            return String.Format("Type: PIMNote    Priority: {0}    {1}",this.Priority, this.Contains);
        }

        public static PIMNote FromString(string str)
        {
            Match mt= Regex.Match(str,@"Type:\s(?<Type>\S+)\s{4}Priority:\s(?<Priority>\S+)\s{4}(?<Contains>\S+)");
            if(mt.Success && mt.Groups["Type"].Value =="PIMNote")
            {
                return new PIMNote(mt.Groups["Priority"].Value, mt.Groups["Contains"].Value);
            }
            else
            {
                return null;
            }
        }
    }

    public class PIMContact
    {
        public PIMContact(){}
        public PIMContact(string Priority, string FirstName, string LastName, string EmailAddress)
        {
            this.Priority = Priority;
            this.FirstName = FirstName;
            this.LastName = LastName;
            this.EmailAddress = EmailAddress;
        }
        public string Priority {get; set;}
        public string FirstName {get; set;}
        public string LastName {get; set;}
        public string EmailAddress {get; set;}
        public override string ToString()
        {
            return String.Format("Type: PIMContact    Priority: {0}    FirstName: {1}    LastName: {2}    {3}:",this.Priority, this.FirstName, this.LastName, this.EmailAddress);
        }

        public static PIMContact FromString(string str)
        {
            Match mt= Regex.Match(str,@"Type:\s(?<Type>\S+)\s{4}Priority:\s(?<Priority>\S+)\s{4}FirstName:\s(?<FirstName>\S+)\s{4}LastName:\s(?<LastName>\S+)\s{4}EmailAddress:\s(?<EmailAddress>\S+)");
            if(mt.Success && mt.Groups["Type"].Value =="PIMContact")
            {
                return new PIMContact(mt.Groups["Priority"].Value, mt.Groups["FirstName"].Value, mt.Groups["LastName"].Value, mt.Groups["EmailAddress"].Value);
            }
            else
            {
                return null;
            }
        }
    }

    public class PIMAppointment
    {
        public PIMAppointment(){}
        public PIMAppointment(string Priority, string PIMDate, string Contains)
        {
            this.Priority = Priority;
            this.PIMDate = PIMDate;
            this.Contains = Contains;
        }
        public string Priority {get; set;}
        public string PIMDate {get; set;}
        public string Contains {get; set;}
        public override string ToString()
        {
            return String.Format("Type: PIMAppointment    Priority: {0}    Date: {1}    {2}",this.Priority, this.PIMDate, this.Contains);
        }

        public static PIMAppointment FromString(string str)
        {
            Match mt= Regex.Match(str,@"Type:\s(?<Type>\S+)\s{4}Priority:\s(?<Priority>\S+)\s{4}Date:\s(?<Date>\S+)\s{4}(?<Contains>\S+)");
            if(mt.Success && mt.Groups["Type"].Value =="PIMAppointment")
            {
                return new PIMAppointment(mt.Groups["Priority"].Value, mt.Groups["Date"].Value, mt.Groups["Contains"].Value);
            }
            else
            {
                return null;
            }
        }
    }

    public class PIMContext:DbContext
    {
        public PIMContext(DbContextOptions<PIMContext> options):base(options){}
        public DbSet<PIMTodo> PIMTodos {get; set;}
        public DbSet<PIMNote> PIMNotes {get; set;}
        public DbSet<PIMContact> PIMContacts {get; set;}
        public DbSet<PIMAppointment> PIMAppointments {get; set;}
        protected override void OnModelCreating (ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<PIMTodo>()
                .HasKey(item => new {item.Priority, item.PIMDate, item.Contains});
            modelBuilder.Entity<PIMNote>()
                .HasKey(item => new {item.Priority, item.Contains});
            modelBuilder.Entity<PIMContact>()
                .HasKey(item => new {item.Priority, item.FirstName, item.LastName, item.EmailAddress});
            modelBuilder.Entity<PIMAppointment>()
                .HasKey(item => new {item.Priority, item.PIMDate, item.Contains});
        }
        protected override void OnConfiguring(DbContextOptionsBuilder options){
            options.ConfigureWarnings(warnings => 
                    warnings.Default(WarningBehavior.Ignore));
        }
    }
}