CREATE TABLE IF NOT EXISTS PIMTodo(
    Priority TEXT NOT NULL,
    PIMDate TEXT NOT NULL,
    Contains TEXT,
    CONSTRAINT pk_PIMTodo PRIMARY KEY (Priority,PIMDate,Contains)
);

CREATE TABLE IF NOT EXISTS PIMNote(
    Priority TEXT NOT NULL,
    Contains TEXT,
    CONSTRAINT pk_PIMNote PRIMARY KEY (Priority,Contains)
);

CREATE TABLE IF NOT EXISTS PIMContact(
    Priority TEXT NOT NULL,
    FirstName TEXT,
    LastName TEXT,
    EmailAddress TEXT,
    CONSTRAINT pk_PIMContact PRIMARY KEY (Priority,FirstName,LastName,EmailAddress)
);

CREATE TABLE IF NOT EXISTS PIMAppointment(
    Priority TEXT NOT NULL,
    PIMDate TEXT NOT NULL,
    Contains TEXT,
    CONSTRAINT pk_PIMAppointment PRIMARY KEY (Priority,PIMDate,Contains)
);

CREATE VIEW IF NOT EXISTS PIMTodoView (PIMEntity) AS
    SELECT 'Type: PIMTodo    Priority: '||Priority||'    Date: '||PIMDate||'    '||Contains
    FROM PIMTodo;

CREATE VIEW IF NOT EXISTS PIMNoteView (PIMEntity) AS
    SELECT 'Type: PIMNote    Priority: '||Priority||'    '||Contains
    FROM PIMNote;

CREATE VIEW IF NOT EXISTS PIMContactView (PIMEntity) AS
    SELECT 'Type: PIMContact    Priority: '||Priority||'    FirstName: '||FirstName||'    LastName: '||LastName||'    EmailAddress: '||EmailAddress
    FROM PIMContact;

CREATE VIEW IF NOT EXISTS PIMAppointmentView (PIMEntity) AS
    SELECT 'Type: PIMAppointment    Priority: '||Priority||'    Date: '||PIMDate||'    '||Contains
    FROM PIMAppointment;

CREATE VIEW IF NOT EXISTS PIMEntities AS
    SELECT PIMEntity
    FROM PIMTodoView
    UNION ALL
    SELECT PIMEntity
    FROM PIMNoteView
    UNION ALL
    SELECT PIMEntity
    FROM PIMContactView
    UNION ALL
    SELECT PIMEntity
    FROM PIMAppointmentView;

REPLACE INTO PIMTodo VALUES ('p1','2017-5-1','todo');
REPLACE INTO PIMNote VALUES ('p2','note');
REPLACE INTO PIMContact VALUES ('p3','ff','ll','ee');
REPLACE INTO PIMAppointment VALUES ('p4','2015-5-2','appointment');