/* global username */
var thisGroupListUsername ="";
var ws;
var strImage='';
var thisGroupName ="";
function connect() {
    ws = new WebSocket("ws://" + document.location.host + "/Project1.3/chat/" + username);

    ws.onmessage = function(event) {
    var userAvatar = $('#userImage1').attr('src');
    var log = document.getElementById("chatForm");
    console.log(event.data);
    var message = JSON.parse(event.data);
    var type = message.type;
    var typeMessage = message.typeMessage;
    var strImageArray = [];
    var thisGroupUserListArray = thisGroupListUsername.split(" ");
    thisGroupUserListArray.length = thisGroupUserListArray.length - 1;
    
    //Single Message process
    if (typeMessage === 'single') {
        console.log("username : "+username + ", gui toi : "+message.to);
        console.log(username === message.to);
        if (type === "text") {
        var count2 = 1;
        var msg1 = `<div class="d-flex justify-content-end" style="margin-bottom: 0.5rem!important;">
                                                                <div class="msg_cotainer_send">`+
                                                                        message.content+
                                                                        `
                                                                </div>
                                                                <div class="img_cont_msg">
                                                                        <img src="`+userAvatar+`" class="rounded-circle user_img_msg">
                                                                </div>
                                                        </div>`;
        var msg2 = `<div class="d-flex justify-content-start" style="margin-bottom: 0.5rem!important;">
                                                                <div class="img_cont_msg">
                                                                        <img src="./images/icons/avatar2.jpg" class="rounded-circle user_img_msg">
                                                                </div>
                                                                <div class="msg_cotainer">`
                                                                        +message.content+									
                                                                `</div>
                                                        </div>`;
        if (username === message.from && count2 === 1) {
            chatForm.insertAdjacentHTML("beforeend", msg1);
            count2++;
        } else {
            if (username.toString() === message.to.toString()) {
                chatForm.insertAdjacentHTML("beforeend", msg2);
                count2++;
            }
        }   
    } else {
        if(type.length == 5) {            
            strImage += message.content;
        } else {
            strImage += message.content;            
            var count3 = 1;
            console.log(username);
            console.log(message.from);
            console.log(strImage.length);
            var msg1 = `<div class="d-flex justify-content-end" style="margin-bottom: 0.5rem!important;">
                                                                       <img class="imageMessage1" src="`+strImage+`">                                                                        
                                                                <div class="img_cont_msg">
                                                                        <img src="`+userAvatar+`" class="rounded-circle user_img_msg">
                                                                </div>
                                                        </div>`;
            var msg2 = `<div class="d-flex justify-content-start" style="margin-bottom: 0.5rem!important;">
                                                                    <div class="img_cont_msg">
                                                                            <img src="./images/icons/avatar2.jpg" class="rounded-circle user_img_msg">
                                                                    </div>

                                                                            <img class="imageMessage2" src="`+strImage+`">									                                                                    
                                                            </div>`;
            if (username === message.from && count3 === 1) {
                chatForm.insertAdjacentHTML("beforeend", msg1);
                count3++;
            } else {
                chatForm.insertAdjacentHTML("beforeend", msg2);
                count3++;
            }   
            strImage = '';
        }
        
    }
    } else {
        if (type === "text") {
        var count2 = 1;
        var msg1 = `<div class="d-flex justify-content-end" style="margin-bottom: 0.5rem!important;">
                                                                <div class="msg_cotainer_send">`+
                                                                        message.content+
                                                                        `
                                                                </div>
                                                                <div class="img_cont_msg">
                                                                        <img src="`+userAvatar+`" class="rounded-circle user_img_msg">
                                                                </div>
                                                        </div>`;
        var msg2 = `<div class="d-flex justify-content-start" style="margin-bottom: 0.5rem!important;">
                                                                <div class="img_cont_msg">
                                                                        <img src="./images/icons/avatar2.jpg" class="rounded-circle user_img_msg">
                                                                </div>
                                                                <div class="msg_cotainer">`
                                                                        +message.content+									
                                                                `</div>
                                                        </div>`;
        if (username === message.from && count2 === 1 && thisGroupName === message.to) {
            chatFormGroup.insertAdjacentHTML("beforeend", msg1);
            count2++;
        } else {
            if (thisGroupUserListArray.includes(username) && thisGroupName === message.to){
                chatFormGroup.insertAdjacentHTML("beforeend", msg2);
                count2++;
            }
        }   
    } else {
        if(type.length == 5) {            
            strImage += message.content;
        } else {
            strImage += message.content;            
            var count3 = 1;
            console.log(username);
            console.log(message.from);
            console.log(strImage.length);
            var msg1 = `<div class="d-flex justify-content-end" style="margin-bottom: 0.5rem!important;">
                                                                       <img class="imageMessage1" src="`+strImage+`">                                                                        
                                                                <div class="img_cont_msg">
                                                                        <img src="./images/icons/avatar1.jpg" class="rounded-circle user_img_msg">
                                                                </div>
                                                        </div>`;
            var msg2 = `<div class="d-flex justify-content-start" style="margin-bottom: 0.5rem!important;">
                                                                    <div class="img_cont_msg">
                                                                            <img src="./images/icons/avatar2.jpg" class="rounded-circle user_img_msg">
                                                                    </div>

                                                                            <img class="imageMessage2" src="`+strImage+`">									                                                                    
                                                            </div>`;
            if (username === message.from && count3 === 1) {
                chatFormGroup.insertAdjacentHTML("beforeend", msg1);
                count3++;
            } else {
                chatFormGroup.insertAdjacentHTML("beforeend", msg2);
                count3++;
            }   
            strImage = '';
        }
    }
    
};

}
}

function send(to) {
    var content = document.getElementById("message").value;
    $('#message').val('');
    var json = JSON.stringify({
        "from":username,
        "to":to,
        "content":content,
        "type":"text",
        "typeMessage": "single"
    });
    ws.send(json);
}
function sendGroup(groupName){
    var content = $('#messageGroup').val();
    $('#messageGroup').val('');
    var json = JSON.stringify({
        "from":username,
        "to":groupName,
        "content":content,
        "type":"text",
        "typeMessage":"group"
    });
    ws.send(json);
}

$('#reload').click(function(){
    connect();
})
   
