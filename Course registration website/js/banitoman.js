var userData = [];
var winTerm = { name: "Winter", depmt: [], allCor: [], allSec: [] };
var fallTerm = { name: "Fall", depmt: [], allCor: [], allSec: [] };
var selTerm;
var selID;
var selStorage;
var selDepmt;
var selCourse;
var addData = [];
var dropData = [];
var username = "Alex";
var user1 = { name: "", password: "" };//regData is divided by term
userData.push(user1);

var winID = [];
var winLocal = [];
var fallID = [];
var fallLocal = [];
var currUser = user1;
var regDataID;
var currRegData = [];

var ul = document.getElementById("myUL");
var li;
if (ul) {
  li = ul.getElementsByTagName("li");
}
var fallScn = { name: "Science", courses: [] };//faculty of Science fall
var winScn = { name: "Science", courses: [] };//faculty of Science winter
fallTerm.depmt.push(fallScn);
winTerm.depmt.push(winScn);

var fallSC1 = {//course 1500
  coName: "MATH 1500",
  name: "Introduction to Calculus",
  id: "M15",
  credit: "Credits : 3",
  sections: []
};
var fallSec1 = {//section 1
  coName: "MATH 1500",
  section: "1",
  id: "M15001",
  time: "8:30-9:20",
  timeInx: 1,
  dayInx: [0, 2, 4],
  place: "room1",
  prof: "mike"
};

var winSC1 = {//course 1700
  coName: "MATH 1700",
  name: "Calculus 2",
  id: "M17",
  credit: "Credits : 3",
  sections: []
};
var winSec1 = {//section 1
  coName: "MATH 1700",
  section: "1",
  id: "M17001",
  time: "8:30-9:20",
  timeInx: 1,
  dayInx: [0, 2, 4],
  place: "room1",
  prof: "mike"
};

fallSC1.sections.push(fallSec1);
winSC1.sections.push(winSec1);
fallScn.courses.push(fallSC1);
winScn.courses.push(winSC1);
fallTerm.allCor.push(fallSC1);
winTerm.allCor.push(winSC1);
fallTerm.allSec.push(fallSec1);
winTerm.allSec.push(winSec1);

var fallSC2 = {//course 1010
  coName: "COMP 1010",
  name: "Introduction to Computer",
  id: "C10",
  credit: "Credits : 3",
  sections: []
};
var fallSec2 = {//section 1
  coName: "COMP 1010",
  section: "1",
  id: "C10001",
  time: "9:30-10:20",
  timeInx: 2,
  dayInx: [0, 2, 4],
  place: "room1",
  prof: "mike"
};

var winSC2 = {//course 1700
  coName: "COMP 2280",
  name: "Computer System",
  id: "C28",
  credit: "Credits : 3",
  sections: []
};
var winSec2 = {//section 1
  coName: "COMP 2280",
  section: "1",
  id: "C28001",
  time: "9:30-10:20",
  timeInx: 2,
  dayInx: [0, 2, 4],
  place: "room1",
  prof: "mike"
};

fallSC2.sections.push(fallSec2);
winSC2.sections.push(winSec2);
fallScn.courses.push(fallSC2);
winScn.courses.push(winSC2);
fallTerm.allCor.push(fallSC2);
winTerm.allCor.push(winSC2);
fallTerm.allSec.push(fallSec2);
winTerm.allSec.push(winSec2);

var fallArt = { name: "Art", courses: [] };//faculty of Art fall
var winArt = { name: "Art", courses: [] };//faculty of Art winter
fallTerm.depmt.push(fallArt);
winTerm.depmt.push(winArt);

var fallAC1 = {//course 1500
  coName: "ECON 1020",
  name: "Introduction to Economics",
  id: "E20",
  credit: "Credits : 3",
  sections: []
};
var fallSec3 = {//section 1
  coName: "ECON 1020",
  section: "1",
  id: "E20001",
  time: "10:30-11:20",
  timeInx: 3,
  dayInx: [0, 2, 4],
  place: "room1",
  prof: "mike"
};

var winAC1 = {//course 1700
  coName: "ECON 3740",
  name: "Industrial Strategy",
  id: "E37",
  credit: "Credits : 3",
  sections: []
};
var winSec3 = {//section 1
  coName: "ECON 3740",
  section: "1",
  id: "E37001",
  time: "10:30-11:20",
  timeInx: 3,
  dayInx: [0, 2, 4],
  place: "room1",
  prof: "mike"
};

fallAC1.sections.push(fallSec3);
winAC1.sections.push(winSec3);
fallArt.courses.push(fallAC1);
winArt.courses.push(winAC1);
fallTerm.allCor.push(fallAC1);
winTerm.allCor.push(winAC1);
fallTerm.allSec.push(fallSec3);
winTerm.allSec.push(winSec3);

var fallAC2 = {//course 1500
  coName: "MRKT 2210",
  name: "Fundamentals of Marketing",
  id: "M22",
  credit: "Credits : 3",
  sections: []
};
var fallSec4 = {//section 1
  coName: "MRKT 2210",
  section: "1",
  id: "M22001",
  time: "10:30-11:20",
  timeInx: 3,
  dayInx: [1, 3],
  place: "room1",
  prof: "mike"
};

var winAC2 = {//course 1700
  coName: "FNCL 3480",
  name: "Financial Markets",
  id: "F38",
  credit: "Credits : 3",
  sections: []
};
var winSec4 = {//section 1
  coName: "FNCL 3480",
  section: "1",
  id: "F38001",
  time: "10:30-11:20",
  timeInx: 3,
  dayInx: [1, 3],
  place: "room1",
  prof: "mike"
};

fallAC2.sections.push(fallSec4);
winAC2.sections.push(winSec4);
fallArt.courses.push(fallAC2);
winArt.courses.push(winAC2);
fallTerm.allCor.push(fallAC2);
winTerm.allCor.push(winAC2);
fallTerm.allSec.push(fallSec4);
winTerm.allSec.push(winSec4);

var fallLer = { name: "Leisure", courses: [] };//faculty of Leisure fall
var winLer = { name: "Leisure", courses: [] };//faculty of Art winter
fallTerm.depmt.push(fallLer);
winTerm.depmt.push(winLer);

var fallLC1 = {//course Eat
  coName: "LEIS 1000",
  name: "Happy Eating",
  id: "L10",
  credit: "Credits : 3",
  sections: []
};
var fallSec5 = {//section 1
  coName: "LEIS 1000",
  section: "1",
  id: "L10001",
  time: "12:30-13:20",
  timeInx: 5,
  dayInx: [0, 2, 4],
  place: "Cafeteria",
  prof: "Friends"
};

var winLC1 = {//course 1700
  coName: "LEIS 2000",
  name: "Happy Cafe",
  id: "L20",
  credit: "Credits : 3",
  sections: []
};
var winSec5 = {//section 1
  coName: "LEIS 2000",
  section: "1",
  id: "L20001",
  time: "12:30-13:20",
  timeInx: 5,
  dayInx: [1, 3],
  place: "Cafeteria",
  prof: "mike"
};

fallLC1.sections.push(fallSec5);
winLC1.sections.push(winSec5);
fallLer.courses.push(fallLC1);
winLer.courses.push(winLC1);
fallTerm.allCor.push(fallLC1);
winTerm.allCor.push(winLC1);
fallTerm.allSec.push(fallSec5);
winTerm.allSec.push(winSec5);

var fallLC2 = {//course play
  coName: "FUNS 1500",
  name: "Movie Time",
  id: "F15",
  credit: "Credits : 3",
  sections: []
};
var fallSec6 = {//section 1
  coName: "FUNS 1500",
  section: "1",
  id: "F15001",
  time: "13:30-14:20",
  timeInx: 6,
  dayInx: [0, 2, 4],
  place: "Lecture room1",
  prof: "mike"
};

var winLC2 = {//course 1700
  coName: "FUNS 2500",
  name: "Game Time",
  id: "F25",
  credit: "Credits : 3",
  sections: []
};
var winSec6 = {//section 1
  coName: "FUNS 2500",
  section: "1",
  id: "F25001",
  time: "13:30-14:20",
  timeInx: 6,
  dayInx: [1, 3],
  place: "Lecture room1",
  prof: "mike"
};

fallLC2.sections.push(fallSec6);
winLC2.sections.push(winSec6);
fallLer.courses.push(fallLC2);
winLer.courses.push(winLC2);
fallTerm.allCor.push(fallLC2);
winTerm.allCor.push(winLC2);
fallTerm.allSec.push(fallSec6);
winTerm.allSec.push(winSec6);

function iniLogin() {
  var winData = [];
  winData.push(winSec1.id);
  var fallData = [];
  fallData.push(fallSec1.id);
  if (typeof (Storage) !== "undefined") {

    sessionStorage.setItem("winRegData", JSON.stringify(winData));
    sessionStorage.setItem("fallRegData", JSON.stringify(fallData));
  }
}
function login() {
  var match = false;
  var u = document.getElementById("uId").value;
  var p = document.getElementById("pWd").value;
  var userInx;
  //it will search user from the userData array
  for (let i = 0; i < userData.length && !match; i++) {
    if (u == userData[i].name && p == userData[i].password) {
      match = true;
      userInx = i;
    }
  }
  if (match) {
    currUser = userData[userInx];
    window.location.href = "homepage.html";
  } else {
    alert("Wrong account or password");
  }
}
//--------------from banitoma2.js that was deleted
function loadStorage() {
  if (typeof (Storage) !== "undefined") {
    let retrievedData = sessionStorage.getItem("winRegData");
    winID = JSON.parse(retrievedData);
    if (winID.length > 0) {
      for (let i = 0; i < winID.length; i++) {
        for (let j = 0; j < winTerm.allSec.length; j++) {

          if (winID[i] == winTerm.allSec[j].id) {
            winLocal.push(winTerm.allSec[j]);
          }
        }
      }
    }
    retrievedData = sessionStorage.getItem("fallRegData");
    fallID = JSON.parse(retrievedData);
    if (fallID.length > 0) {
      for (let i = 0; i < fallID.length; i++) {
        for (let j = 0; j < fallTerm.allSec.length; j++) {

          if (fallID[i] == fallTerm.allSec[j].id) {
            fallLocal.push(fallTerm.allSec[j]);
          }
        }
      }
    }
  } else {
    winLocal.push(winSec1);
    fallLocal.push(fallSec1);
  }
}
function showDepartment() {
  let x = document.getElementById("department");
  let s = document.getElementById("term").selectedIndex;
  currRegData = [];

  if (s == 1) {
    selTerm = winTerm;
    selStorage = "winRegData";
    regDataID = winID;
    currRegData = winLocal;

  } else if (s == 2) {
    selTerm = fallTerm;
    selStorage = "fallRegData";
    regDataID = fallID;
    currRegData = fallLocal;
  }
  if (x.style.visibility === 'hidden') {
    x.style.visibility = 'visible';
  }
  while (x.options.length > 1) {
    x.remove(x.options.length - 1);
  }
  for (let i = 0; i < selTerm.depmt.length; i++) {
    let newOp = document.createElement("option");
    newOp.text = selTerm.depmt[i].name;
    x.add(newOp);
  }
}
function showSearchBar() {
  var y = document.getElementById("searchBar");
  var s = document.getElementById("button");
  var z = document.getElementById("scrollPart");
  if (y.style.visibility === 'hidden') {
    y.style.visibility = 'visible';
  }
  if (s.style.visibility === 'hidden') {
    s.style.visibility = 'visible';
  }
  if (z.style.visibility === 'hidden') {
    z.style.visibility = 'visible';
  }
}
function search() {
  var input = document.getElementById("searchBar").value;
  var newlist = searchByInput(input);
  if (newlist.length > 0) {
    for (let j = 0; j < li.length; j++) {
      li[j].innerHTML = "";
      li[j].draggable = "false";
    }
    for (let i = 0; i < newlist.length; i++) {
      li[i].id = newlist[i].id;
      li[i].innerHTML = newlist[i].coName;
      li[i].innerHTML += "<br>" + newlist[i].name;
      li[i].innerHTML += "<br>" + newlist[i].credit;
      li[i].innerHTML += "<br>" + '<button id = ' + newlist[i].id + ' onclick = ' + "showSection(event)" + '>Check Sections</button>';
    }
  }
}
function searchByInput(input) {
  var result = [];
  for (let i = 0; i < selTerm.allCor.length; i++) {
    if (((selTerm.allCor[i].coName).toUpperCase()).indexOf(input.toUpperCase()) >= 0) {
      result.push(selTerm.allCor[i]);
    }
  }
  return result;
}
function iniAddPage() {
  iniTable();
  if (currRegData.length > 0) {
    for (let i = 0; i < currRegData.length; i++) {
      let currSec = currRegData[i];
      for (let j = 0; j < currSec.dayInx.length; j++) {
        let currInx = currSec.dayInx[j];
        let currRow = document.getElementById("myTable").rows[currSec.timeInx].cells;
        currRow[currInx].innerHTML = currSec.coName + " " + currSec.section;
        currRow[currInx].innerHTML += "<br>" + currSec.place;
        currRow[currInx].innerHTML += "<br>" + currSec.time;
      }
    }
  }
}
function iniDropPage() {
  let s = document.getElementById("term").selectedIndex;
  currRegData = [];

  if (s == 1) {
    selTerm = winTerm;
    selStorage = "winRegData";
    regDataID = winID;
    currRegData = winLocal;

  } else if (s == 2) {
    selTerm = fallTerm;
    selStorage = "fallRegData";
    regDataID = fallID;
    currRegData = fallLocal;
  }
  iniTable();
  if (currRegData.length > 0) {
    for (let i = 0; i < currRegData.length; i++) {
      let currSec = currRegData[i];
      for (let j = 0; j < currSec.dayInx.length; j++) {
        let currInx = currSec.dayInx[j];
        let currRow = document.getElementById("myTable").rows[currSec.timeInx].cells;
        currRow[currInx].id = currSec.id;
        currRow[currInx].draggable = "true";
        currRow[currInx].innerHTML = currSec.coName + " " + currSec.section;
        currRow[currInx].innerHTML += "<br>" + currSec.place;
        currRow[currInx].innerHTML += "<br>" + currSec.time;
      }
    }
  }
}
////////////////////////////////////////////////////
function iniTable() {
  for (let i = 1; i <= 6; i++) {
    for (let j = 0; j < 5; j++) {
      let currRow = document.getElementById("myTable").rows[i].cells;
      currRow[j].innerHTML = "";
    }
  }
}
function showAllCourse() {

  for (let j = 0; j < li.length; j++) {
    li[j].innerHTML = "";
    li[j].draggable = "false";
  }
  for (let i = 0; i < selTerm.allCor.length; i++) {
    li[i].id = selTerm.allCor[i].id;
    li[i].innerHTML = selTerm.allCor[i].coName;
    li[i].innerHTML += "<br>" + selTerm.allCor[i].name;
    li[i].innerHTML += "<br>" + selTerm.allCor[i].credit;
    li[i].innerHTML += "<br>" + '<button id = ' + selTerm.allCor[i].id + ' onclick = ' + "showSection(event)" + '>Check Sections</button>';
  }
}
function showCourse() {

  let index = document.getElementById("department").selectedIndex - 1;
  if (index > -1) {
    selDepmt = selTerm.depmt[index];

    for (let j = 0; j < li.length; j++) {
      li[j].innerHTML = "";
      li[j].draggable = "false";
    }
    for (let i = 0; i < selDepmt.courses.length; i++) {
      li[i].id = selDepmt.courses[i].id;
      li[i].innerHTML = selDepmt.courses[i].coName;
      li[i].innerHTML += "<br>" + selDepmt.courses[i].name;
      li[i].innerHTML += "<br>" + selDepmt.courses[i].credit;
      li[i].innerHTML += "<br>" + '<button id = ' + selDepmt.courses[i].id + ' onclick = ' + "showSection(event)" + '>Check Sections</button>';
    }
  } else {
    showAllCourse();
  }
}
function showSection(ev) {
  let cid = ev.target.id;
  let found = false;

  for (let i = 0; i < selTerm.allCor.length && !found; i++) {
    let currCor = selTerm.allCor[i];
    if (currCor.id == cid) {
      found = true;
      selCourse = currCor;
    }
  }
  if (selCourse == null) {
    alert("null");
  }

  for (let j = 0; j < li.length; j++) {
    li[j].innerHTML = "";
    li[j].draggable = "false";
  }
  for (let k = 0; k < selCourse.sections.length; k++) {
    li[k].id = selCourse.sections[k].id;
    li[k].draggable = "true";
    li[k].innerHTML = selCourse.sections[k].coName + " Section " + selCourse.sections[k].section;
    li[k].innerHTML += "<br>" + selCourse.sections[k].time;
    li[k].innerHTML += "<br>" + selCourse.sections[k].place;
    li[k].innerHTML += "<br>" + selCourse.sections[k].prof;
  }
  document.getElementById("note").innerHTML = "Note: Drag a section block to the timetable to add a course.";
}
var dragSec = null;

document.ondragstart = function (event) {

  let found = false;
  for (let i = 0; i < selTerm.allSec.length && !found; i++) {
    let currSec = selTerm.allSec[i];
    if (currSec.id == event.target.id) {
      found = true;
      dragSec = currSec;
    }
  }
}
function overTable(ev) {
  ev.preventDefault();
  if (dragSec == null) {
    //alert("null");
  }
  for (let i = 0; i < dragSec.dayInx.length; i++) {
    let currInx = dragSec.dayInx[i];
    let currRow = document.getElementById("myTable").rows[dragSec.timeInx].cells;
    currRow[currInx].style.backgroundColor = 'coral';
  }
}
function overBox(ev) {
  ev.preventDefault();
  document.getElementById("box").style.backgroundColor = "rgba(255,0,0,0.4)";
}

function dropIntable(ev) {
  ev.preventDefault();
  if (dragSec == null) {
    alert("Please select a section");
  }
  let same = false;
  let conflict = false;
  for (let i = 0; i < currRegData.length; i++) {
    if (currRegData[i].id == dragSec.id) {
      same = true;
    }
    if (currRegData[i].dayInx[0] == dragSec.dayInx[0] && currRegData[i].timeInx == dragSec.timeInx) {
      conflict = true;
    }
  }
  for (let j = 0; j < addData.length; j++) {
    if (addData[j].id == dragSec.id) {
      same = true;
    }
    if (addData[j].dayInx[0] == dragSec.dayInx[0] && addData[j].timeInx == dragSec.timeInx) {
      conflict = true;
    }
  }
  if (!same && !conflict) {
    addData.push(dragSec);
    for (let i = 0; i < dragSec.dayInx.length; i++) {
      let currInx = dragSec.dayInx[i];
      let currRow = document.getElementById("myTable").rows[dragSec.timeInx].cells;
      currRow[currInx].id = dragSec.id;
      currRow[currInx].innerHTML = dragSec.coName + " " + dragSec.section;
      currRow[currInx].innerHTML += "<br>" + dragSec.place;
      currRow[currInx].innerHTML += "<br>" + dragSec.time;
      currRow[currInx].innerHTML += "<br>" + '<button id = ' + dragSec.id + ' onclick = ' + "cancel(event)" + '>X</button>';
    }
  } else if (same) {
    alert("Duplicate course!");
  } else if (conflict) {
    alert("Time conflict!");
  }
}
function tableEnd() {
  if (dragSec == null) {
    // alert("null");
  }
  for (let i = 0; i < dragSec.dayInx.length; i++) {
    let currInx = dragSec.dayInx[i];
    let currRow = document.getElementById("myTable").rows[dragSec.timeInx].cells;
    currRow[currInx].style.backgroundColor = 'transparent';
  }
  dragSec = null;
}
function cancel(ev) {
  let cid = ev.target.id;
  let found = false;
  let canSec;
  let canInx;
  for (let i = 0; i < addData.length && !found; i++) {
    let currSec = addData[i];
    if (currSec.id == cid) {
      found = true;
      canSec = currSec;
      canInx = i;
    }
  }
  for (let i = 0; i < canSec.dayInx.length; i++) {
    let currInx = canSec.dayInx[i];
    let currRow = document.getElementById("myTable").rows[canSec.timeInx].cells;
    currRow[currInx].innerHTML = "";
  }
  addData.splice(canInx, 1);
}

function dropInBox(ev) {
  ev.preventDefault();
  if (dragSec == null) {
    alert("null");
  }
  dropData.push(dragSec);
  for (let i = 0; i < dragSec.dayInx.length; i++) {
    let currInx = dragSec.dayInx[i];
    let currRow = document.getElementById("myTable").rows[dragSec.timeInx].cells;
    currRow[currInx].innerHTML = "";
  }
}
function boxEnd() {
  document.getElementById("box").style.backgroundColor = 'transparent';
  dragSec = null;
}
function submitAdd() {
  let string = "";
  if(addData.length <= 0){
    string = "You did not add a course.";
  } else {
    string = "Are you sure you want to register the following course(s)?";
    for (let j = 0; j < addData.length; j++) {
      string += "\n\t" + addData[j].coName + " " + addData[j].section;
    }
  }
  if (confirm(string)) {
    if(addData.length > 0){
      for (let i = 0; i < addData.length; i++) {
        regDataID.push(addData[i].id);
        currRegData.push(addData[i]);
      }
      if (typeof (Storage) !== "undefined") {

        sessionStorage.setItem(selStorage, JSON.stringify(regDataID));
      }
      alert("Successfully Registered.");
      iniAddPage();
      addData = [];
    }
  }
}

function submitDrop() {
  let string = "";
  if(dropData.length <= 0){
    string = "Please remove course(s) you want to drop.";
  }else {
    string = "Are you sure you want to drop the following course(s)?";
    for (let j = 0; j < dropData.length; j++) {
      string += "\n\t" + dropData[j].coName + " " + dropData[j].section;
    }
  }
    if (confirm(string)) {
      if(dropData.length > 0){
        if (typeof (Storage) !== "undefined") {
          for (let i = 0; i < dropData.length; i++) {
            for (let j = 0; j < regDataID.length && regDataID.length > 0; j++) {
              if (dropData[i].id == regDataID[j]) {
                regDataID.splice(j, 1);
                currRegData.splice(j, 1);
                j--;
              }
            }
          }
          sessionStorage.setItem(selStorage, JSON.stringify(regDataID));
        } else {
          for (let i = 0; i < dropData.length; i++) {
            for (let j = 0; j < currRegData.length && currRegData.length > 0; j++) {
              if (dropData[i].id == currRegData[j].id) {
                currRegData.splice(j, 1);
                j--;
              }
            }
          }
        }
        alert("Successfully dropped course(s).");
        dropData = [];
      }
    }
}
function iniSchedule() {
  let s = document.getElementById("term").selectedIndex;
  currRegData = [];

  if (s == 1) {
    selTerm = winTerm;
    currRegData = winLocal;

  } else if (s == 2) {
    selTerm = fallTerm;
    currRegData = fallLocal;
  }
  iniTable();
  if (currRegData.length > 0) {
    for (let i = 0; i < currRegData.length; i++) {
      let currSec = currRegData[i];
      for (let j = 0; j < currSec.dayInx.length; j++) {
        let currInx = currSec.dayInx[j];
        let currRow = document.getElementById("myTable").rows[currSec.timeInx].cells;
        currRow[currInx].id = currSec.id;
        currRow[currInx].innerHTML = currSec.coName + " " + currSec.section;
        currRow[currInx].innerHTML += "<br>" + currSec.place;
        currRow[currInx].innerHTML += "<br>" + currSec.time;
      }
    }
  }
}