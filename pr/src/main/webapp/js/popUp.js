$(document).ready(function(){
    ///////friend
    var e = document.getElementById('friendsButton');
    e.onmouseover = function() {
      document.getElementById('popUpFriends').style.display = 'block';
    }
    e.onmouseout = function() {
      document.getElementById('popUpFriends').style.display = 'none';
    }
    
    ///////group
    var e = document.getElementById('groupButton');
    e.onmouseover = function() {
      document.getElementById('popUpGroup').style.display = 'block';
    }
    e.onmouseout = function() {
      document.getElementById('popUpGroup').style.display = 'none';
    }
    
    ////////check
    var e = document.getElementById('request');
    e.onmouseover = function() {
      document.getElementById('popUpCheck').style.display = 'block';
    }
    e.onmouseout = function() {
      document.getElementById('popUpCheck').style.display = 'none';
    }
    
    /////////add
    var e = document.getElementById('add');
    e.onmouseover = function() {
      document.getElementById('popUpAdd').style.display = 'block';
    }
    e.onmouseout = function() {
      document.getElementById('popUpAdd').style.display = 'none';
    }
    
    ////////info
    var e = document.getElementById('userImageSet');
    e.onmouseover = function() {
      document.getElementById('popUpInfo').style.display = 'block';
    }
    e.onmouseout = function() {
      document.getElementById('popUpInfo').style.display = 'none';
    }
    
    ///////add member
    var e = document.getElementById('groupAddMember');
    e.onmouseover = function() {
      document.getElementById('popUpAddMember').style.display = 'block';
    }
    e.onmouseout = function() {
      document.getElementById('popUpAddMember').style.display = 'none';
    }
})
