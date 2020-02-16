var count = 0;
var countConnect = 0;
var array = [];
var thisFriendName;




function loadChat(data){
    data.forEach((temp) => {
        var senderid = temp.senderid.toString();
        var content = temp.content.toString();
        var time = temp.time.toString();
        var userAvatar = $('#userImage1').attr('src');
        console.log(senderid,content,time);
        var chatForm = document.getElementById('chatForm');
        var avtId = 'avatar'+thisFriendName;
        if (content.slice(0,10) !== 'data:image') {
                var msg1 = `<div class="d-flex justify-content-end " style="margin-bottom: 0.5rem!important;">
                                                                <div class="msg_cotainer_send">`+
                                                                        content+
                                                                        `
                                                                </div>
                                                                <div class="img_cont_msg">
                                                                        <img src="`+userAvatar+`" class="rounded-circle user_img_msg">
                                                                </div>
                                                        </div>`;
                var msg2 = `<div class="d-flex justify-content-start" style="margin-bottom: 0.5rem!important;">
                                                                <div class="img_cont_msg">
                                                                        <img src="`+$('#'+avtId+'').attr('src')+`" class="rounded-circle user_img_msg">
                                                                </div>
                                                                <div class="msg_cotainer">`
                                                                        +content+									
                                                                `</div>
                                                        </div>`;
                if (senderid ===  id) {
                    chatForm.insertAdjacentHTML("afterbegin",msg1);
                }
                else {
                    chatForm.insertAdjacentHTML("afterbegin",msg2);
                }
            } else {
                var msg1 = `<div class="d-flex justify-content-end " style="margin-bottom: 0.5rem!important;">
                                                                <img class="imageMessage1" src="`+content+`">
                                                                <div class="img_cont_msg">
                                                                        <img src="`+userAvatar+`" class="rounded-circle user_img_msg">
                                                                </div>
                                                        </div>`;
                var msg2 = `<div class="d-flex justify-content-start" style="margin-bottom: 0.5rem!important;">
                                                                <div class="img_cont_msg">
                                                                        <img src="`+$('#'+avtId+'').attr('src')+`" class="rounded-circle user_img_msg">
                                                                </div>
                                                                <img class="imageMessage2" src="`+content+`">
                                                        </div>`;
                                                  
                if (senderid ===  id) {
                    chatForm.insertAdjacentHTML("afterbegin",msg1);
                    console.log("gui anh sendid :"+senderid+", this userid :"+id+", add msg1");    
                }
                else {
                    chatForm.insertAdjacentHTML("afterbegin",msg2);
                    console.log("gui anh sendid :"+senderid+", this userid :"+id+", add msg1");   
                }
            }
    })
}
function load(friend){
    console.log("friend load chat :: "+friend);
    $.ajax({
        url : 'LoadChat',
        type : 'post',
        dataType : 'JSON',
        data : {userid : id, friend : friend},
        success : loadChat
    });
}
function takeFriendsList(data) {
    contacts_body.innerHTML='';    
    friends.innerHTML='';
    data.forEach((element) => {
        var friendName = element.toString();
        console.log(friendName);       
        var id = 'avatar'+friendName;
        var string = `<li class="active">
                            <a id="`+friendName+`" href="#" >
                                <div class="d-flex bd-highlight">
                                        <div class="img_cont">
                                                <img src="`+$('#'+id+'').attr('src')+`" class="rounded-circle user_img">
                                                <span class="online_icon"></span>
                                        </div>
                                        <div class="user_info">
                                                <span id="">`+friendName+`</span>
                                                <p></p>
                                        </div>
                                </div>
                            </a>
                        </li>`;
        
        friends.insertAdjacentHTML("beforeend", string);    
        $(document).ready(function(){
            $('#'+friendName+'').click(function(){
                    $('#chatAll').removeClass('hiddenForm');
                    $('#friendAvatar').attr('src',$('#'+id+'').attr('src'))
                });
        })
        $(document).ready(function(){
            $('#'+friendName+'').click(function(){
                    $('#chatAllGroup').addClass('hiddenForm');
                });
        })
        $(document).ready(function(){
            $('#'+friendName+'').click(function(){
                
                console.log(friendName);
                console.log(thisFriendName);
                if (thisFriendName !== friendName) {
                    var chat = document.getElementById("chatForm");
                    chat.innerHTML = "";
                    load(friendName);
                    $('#chatForm').animate({
                    scrollTop: $('#chatForm').get(0).scrollHeight
                    }, 1000);
                }
            })
        })
        $(document).ready(function(){
            $('#'+friendName+'').click(function(){
                var friendNameShow = document.getElementById('friendNameShow');
                friendNameShow.innerHTML = friendName;
                thisFriendName = friendName
        })
    });    
    })                   
};
$(document).ready(function(){
    $('#friendsButton').click(function(){
        $('#friends').val('');
        $.ajax({
                        url : 'LoadFriends',
                        type : 'POST',
                        dataType : 'JSON',
                        data : {id : id},
                        success : takeFriendsList
                    });
    })
$(document).ready(function(){
    
    $('#addFriend').click(function(){
    var friendInf = prompt('Enter username or email :');
    $.ajax({
        url : 'AddFriendProcess',
        type : 'post',
        data : {friendInf : friendInf, username : username},
        success : function(data){
        $('#addFriendResult').html(data);
    }
    })
})

})
})

function loadST(){
    $.ajax({
    url : 'LoadFriends',
    type : 'POST',
    dataType : 'JSON',
    data : {id : id},
    success : takeFriendsList
    });
    };

function setFriendsAvatar(data){
    data.forEach((element) => {
        var name = element.username;
        var avatar = element.avatar;
        console.log(name,avatar.slice(0,10));
        var place = document.getElementById("listUserImage");
        var str = `<img src="`+avatar+`" id="avatar`+name+`" style="display: none;"/>`;
        place.insertAdjacentHTML("afterbegin",str);
    })
    loadST();
}

function getAndSetFriendsAvatar() {
            $.ajax({
                url : 'LoadFriendsAvatar',
                type : 'post',
                dataType : 'JSON',
                data : {id : id},
                success : setFriendsAvatar
            })
}

getAndSetFriendsAvatar();






