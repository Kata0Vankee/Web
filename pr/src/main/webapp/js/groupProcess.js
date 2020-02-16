var count = 0;
var countConnect = 0;
var array = [];

/////////////process group avatar
function setGroupsAvatar(data){
    data.forEach((element) => {
        var groupName = element.groupName;
        var avatar = element.avatar;
        console.log(groupName,avatar.slice(0,10));
        var place = document.getElementById("listGroupImage");
        place.innerHTML = "";
        if (avatar === 'null') {
            setGroupAvatarDefault(groupName);
            var str = `<img src="`+$('#groupAvatarDefault').attr('src')+`" id="avatarGroup`+groupName+`" style="display: none;"/>`;
            place.insertAdjacentHTML("afterbegin",str);
        } else {
            var str = `<img src="`+avatar+`" id="avatarGroup`+groupName+`" style="display: none;"/>`;
            place.insertAdjacentHTML("afterbegin",str);
        }
    })
}

function getGroupsAvatar() {
            $.ajax({
                url : 'LoadGroupsAvatar',
                type : 'post',
                dataType : 'JSON',
                data : {id : id},
                success : setGroupsAvatar
            })
}

//////////////load chat group
function loadChatGroup(data){
    data.forEach((temp) => {
        var senderName = temp.senderName.toString();
        var content = temp.content.toString();
        var time = temp.time.toString();
        console.log(senderName,content,time);
        var chatForm = document.getElementById('chatFormGroup');
        if (content.slice(0,10) !== 'data:image') {
                var msg1;
                var msg2;
                if (senderName !== username){                   
                    var msg2 = `<div class="d-flex justify-content-start" style="margin-bottom: 0.5rem!important;">
                                                                    <div class="img_cont_msg">
                                                                            <img src="`+$('#avatar'+senderName+'').attr('src')+`" class="rounded-circle user_img_msg">
                                                                    </div>
                                                                    <div class="msg_cotainer">`
                                                                            +content+									
                                                                    `</div>
                                                            </div>`;
                } else {
                    var msg1 = `<div class="d-flex justify-content-end " style="margin-bottom: 0.5rem!important;">
                                                                <div class="msg_cotainer_send">`+
                                                                        content+
                                                                        `
                                                                </div>
                                                                <div class="img_cont_msg">
                                                                        <img src="`+$('#userImage1').attr('src')+`" class="rounded-circle user_img_msg">
                                                                </div>
                                                        </div>`;
                }
                if (thisGroupName === temp.to.toString()){
                    if (senderName ===  username) {
                    chatForm.insertAdjacentHTML("afterbegin",msg1);
                }
                else {
                    chatForm.insertAdjacentHTML("afterbegin",msg2);
                }
                }
            } else {
                var msg1 = `<div class="d-flex justify-content-end " style="margin-bottom: 0.5rem!important;">
                                                                <img class="imageMessage1" src="`+content+`">
                                                                <div class="img_cont_msg">
                                                                        <img src="`+$('#avatar'+senderName+'').attr('src')+`" class="rounded-circle user_img_msg">
                                                                </div>
                                                        </div>`;
                var msg2 = `<div class="d-flex justify-content-start" style="margin-bottom: 0.5rem!important;">
                                                                <div class="img_cont_msg">
                                                                        <img src="`+$('#avatar'+senderName+'').attr('src')+`" class="rounded-circle user_img_msg">
                                                                </div>
                                                                <img class="imageMessage2" src="`+content+`">
                                                        </div>`;
                if (senderName ===  username) {
                    chatForm.insertAdjacentHTML("afterbegin",msg1);
                }
                else {
                    chatForm.insertAdjacentHTML("afterbegin",msg2);
                }
            }
    })
}
function loadGroupChat(group){
    console.log("group load chat :: "+group);
    $.ajax({
        url : 'LoadChatGroup',
        type : 'post',
        dataType : 'JSON',
        data : {userid : id, groupName : group},
        success : loadChatGroup
    });
}
function takeGroupsList(data) {
    contacts_body.innerHTML='';    
    friends.innerHTML='';
    data.forEach((element) => {
        var groupName = element.groupName;
        console.log(groupName);
        var string = `<li class="active">
                            <a id="`+groupName+`" href="#" >
                                <div class="d-flex bd-highlight">
                                        <div class="img_cont">
                                                <img src="`+$('#groupAvatarDefault').attr('src')+`" class="rounded-circle user_img">
                                                <span class="online_icon"></span>
                                        </div>
                                        <div class="user_info">
                                                <span id="">`+groupName+`</span>
                                                <p>`+element.groupListUsername+`</p>
                                        </div>
                                </div>
                            </a>
                        </li>`;
        
        friends.insertAdjacentHTML("beforeend", string);    
        $(document).ready(function(){
            $('#'+groupName+'').click(function(){
                    $('#chatAllGroup').removeClass('hiddenForm');
                    $('#groupAvatar1').attr('src',$('#groupAvatarDefault').attr('src'));
                });
        })
        $(document).ready(function(){
            $('#'+groupName+'').click(function(){
                    $('#chatAll').addClass('hiddenForm');
                });
        })
        $(document).ready(function(){
            $('#'+groupName+'').click(function(){
                
                console.log("group luc bam :"+groupName);
                console.log("this group name"+thisGroupName);
                if (thisGroupName !== groupName) {
                    var chat = document.getElementById("chatFormGroup");
                    chat.innerHTML = "";
                    loadGroupChat(groupName);
                    $('#chatFormGroup').animate({
                    scrollTop: $('#chatForm').get(0).scrollHeight
                    }, 1000);
                }
            })
        })
        $(document).ready(function(){
            $('#'+groupName+'').click(function(){
                var groupNameShow = document.getElementById('groupNameShow');
                groupNameShow.innerHTML = groupName;
                thisGroupName = groupName;
                thisGroupListUsername = element.groupListUsername;
        })
    }); 
        $(document).ready(function(){
            $('#'+groupName+'').click(function(){
                
                console.log("list group luc bam :"+element.groupListUsername);
                console.log("this group name :"+thisGroupListUsername);
                //if (thisGroupListUsername !== element.groupListUsername) {
                //    var chat = document.getElementById("chatFormGroup");
                //    chat.innerHTML = "";
                //    loadGroupChat(groupName);
                //    $('#chatFormGroup').animate({
                //   scrollTop: $('#chatForm').get(0).scrollHeight
                //    }, 1000);
                //}
            })
        })
           
    })                   
};
$(document).ready(function(){
    $('#groupButton').click(function(){
        $('#friends').val('');
        $.ajax({
                        url : 'LoadGroup',
                        type : 'POST',
                        dataType : 'JSON',
                        data : {id : id},
                        success : takeGroupsList
                    });
    })


})


function setGroupAvatarDefault(groupName){
    $.ajax({
        url : 'ChangeAvatarGroup',
        data : {avatar : $('#groupAvatarDefault').attr('src'), groupName:groupName},
        type : 'post'
    })
    console.log("da set avatar default");
}







