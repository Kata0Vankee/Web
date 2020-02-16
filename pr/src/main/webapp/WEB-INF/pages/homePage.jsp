<%@page import="java.util.*" import="java.io.*" %>
<% String username = (String) request.getAttribute("username"); %>
<% String firstname = (String) request.getAttribute("firstname"); %>
<% String lastname = (String) request.getAttribute("lastname"); %>
<% String id = (String) request.getAttribute("id"); %>
<!DOCTYPE html>
<html>
	<head>
		<title>Chat</title>
                <script>
                    var username = '<%=username%>';
                    var firstname = '<%=firstname%>';
                    var lastname = '<%=lastname%>';
                    var id = '<%=id%>';
                    console.log(username,firstname,lastname,id);
                </script>                   
                <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
                <script src="https://kit.fontawesome.com/3f0bc93e42.js" crossorigin="anonymous"></script>
                <link rel="stylesheet" href="css/chatbox.css"/>   
                <link rel="stylesheet" href="css/popUp.css"/>  
                <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.js"></script>
                                             
                <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
                <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
                <script type="text/javascript" src="./js/websocket.js"></script> 
                <script language="javascript" src="./js/firstLoad.js"></script>       
                <script language="javascript" src="./js/processChangeAvatar.js"></script>                

        </head>
        <body>     
            <script language="javascript" src="./js/friendProcess.js"></script>
            <script language="javascript" src="./js/groupProcess.js"></script> 
            <div class="container-fluid h-100">
			<div class="row justify-content-center h-100" style="background-image: url('images/hinhnen.jpg'); background-size: cover;">
                            <div id="listUserImage" class="list_image" style="display: none;"></div>    
                            <img src="./images/icons/default.jpg" id="userImageDefault" style="display: none;"/>
                            
                            <div id="listGroupImage" style="display: none;"></div>
                            <img src="./images/group.jpg" id="groupAvatarDefault" style="display: none;"/>
                            
                          
                            <div class="user_info1">
                                    <div class="user_info_name">
                                       <!-- <p style = "font-size: 200%;position: absolute;top: 25%;color: white; right: 10%;">
                                            Username: <%= username %>
                                        </p> -->
                                    </div>
                                    <div class="user_info_image">
                                        <a href="#" id="userImageSet" onclick="document.getElementById('userImageInput').click()">
                                            <input type='file' accept="image/*" id="userImageInput" style="display: none;"/>                                                                                                                                  
                                            <div class="img_cont user_info_image_image">
                                                <img id="userImage1" src="#" class="rounded-circle user_image" style="width: 100%; height: 80%; margin-top: 20%;">                                               
                                            </div>
                                        </a>
                                    </div>
                                    <div id="popUpInfo" class="pop_up_info" style="display: none;">
                                        <p class="pop_up_note" style="left:10%;">Click to change avatar</p>
                                    </div>
                            </div>
                            <div id="friendsArea" class="col-md-4 col-xl-3 chat contacts_all">
                                <div class="card mb-sm-3 mb-md-0 contacts_card contacts_all_area">
                                    <div class="card-header">
                                            <!--<div class="input-group">
                                                    <input type="text" placeholder="Search..." name="" class="form-control search">
                                                    <div class="input-group-prepend">
                                                            <span class="input-group-text search_btn"><i class="fas fa-search"></i></span>
                                                    </div>
                                            </div>-->
                                            <div>
                                                <a href="#" id="friendsButton" style="color:white; margin: 25px;" class="icon-class">
                                                    <i class="fas fa-user-alt"></i>
                                                </a>       
                                                <a href="#" id="groupButton" style="color:white; margin: 25px;" class="icon-class">
                                                    <i class="fas fa-users"></i>
                                                </a>                            
                                                <a href="#" id="request" style="color:white; margin: 25px;" class="icon-class">
                                                    <i class="fas fa-user-check"></i>
                                                </a>
                                                <a href="#" id="add" style="color:white; margin: 25px;" class="icon-class">
                                                    <i class="fas fa-plus-square"></i>
                                                </a>
                                            </div>
                                            <div id="popUpFriends" class="pop_up">
                                                <p class="pop_up_note">Friends List</p>
                                            </div>
                                            <div id="popUpGroup" class="pop_up" style="left: 30%;">
                                                <p class="pop_up_note">Groups List</p>
                                            </div>
                                            <div id="popUpCheck" class="pop_up" style="left: 50%;">
                                                <p class="pop_up_note">Friend Requests</p>
                                            </div>
                                            <div id="popUpAdd" class="pop_up" style="left: 75%; width: 40%; height: 10%;">
                                                <p class="pop_up_note">Add friend or Create new group</p>
                                            </div>
                                    </div>
                                    <div  class="card-body contacts_body">
                                        <div id="contacts_body" class="card-header" >

                                        </div>
                                        <ui id="friends" class="contacts">


                                        </ui>
                                    </div>
                                    <div class="card-footer"></div>
                            </div></div>
                        <!--SINGLE CHAT-->
                        <div class="col-md-8 col-xl-6 chat chat_all">
                                    <div id="chatAll" class="card chat_all_area  hiddenForm">
                                        <div class="card-header msg_head" style="border-color: wheat;">
                                                    <div class="d-flex bd-highlight">

                                                            <div class="img_cont">
                                                                    <img id="friendAvatar" src="./images/icons/avatar2.jpg" class="rounded-circle user_img">
                                                                    <span class="online_icon"></span>
                                                            </div>

                                                            <div class="user_info">
                                                                <span>Chat with <p id="friendNameShow" style="font-size: 150%; color: white;">Khalid</p></span>
                                                            </div>

                                                            <div class="video_cam">
                                                                    <span><i class="fas fa-video"></i></span>
                                                                    <span><i class="fas fa-phone"></i></span>
                                                            </div>
                                                            <div class="username_info">
                                                                <span id='name'><%=firstname +" "+ lastname%></span>
                                                            </div>
                                                    </div>
                                                    <span id="action_menu_btn"><i class="fas fa-ellipsis-v"></i></span>
                                                    <div class="action_menu">
                                                            <ul>
                                                                    <li><i class="fas fa-user-circle"></i> View profile</li>
                                                                    <li><i class="fas fa-users"></i> Add to close friends</li>
                                                                    <li><i class="fas fa-plus"></i> Add to group</li>
                                                                    <li><i class="fas fa-ban"></i> Block</li>
                                                            </ul>
                                                    </div>
                                            </div>

                                            <div id="chatForm" class="card-body msg_card_body" style="/*background-image: url('images/hoabingan.jpg');*/ 
                                                 background-size: cover; overflow: auto;" >


                                            </div>

                                            <div class="card-footer">
                                                    <div class="input-group">
                                                        <div class="input-group-append">
                                                                <a href="" onclick="document.getElementById('imageInput').click(); return false">
                                                                    <i class="fas fa-paperclip"></i>
                                                                </a>    
                                                        </div>
                                                        <div id="imageSend" class="imageSend hiddenForm">
                                                            <img id="image" class="image" src="#"/>
                                                        </div>
                                                        <input type='file' accept="image/*" id="imageInput" style="display: none;"/>
                                                        <textarea id="message" name="" class="form-control type_msg" placeholder="Type your message..."></textarea>
                                                        <div class="input-group-append">
                                                            <button id="submitMessage" class="btn btn-primary mb-2" style="height: 100%">                                                          
                                                                <i class="fas fa-location-arrow"></i>				
                                                            </button>
                                                        </div>
                                                    </div>
                                            </div>
                                    </div>

                                    <div id="chatAllGroup" class="card chat_all_area  hiddenForm">
                                        <div class="card-header msg_head" style="border-color: wheat;">
                                                    <div class="d-flex bd-highlight">

                                                            <div class="img_cont">
                                                                <a id="groupInfo" href="#" onclick="document.getElementById('groupAvatarInput').click()">
                                                                    <img id ="groupAvatar1" src="" class="rounded-circle user_img">
                                                                    <input type='file' accept="image/*" id="groupAvatarInput" style="display: none;"/> 
                                                                    <span class="online_icon"></span>
                                                                </a>
                                                            </div>
                                                            <div class="user_info">
                                                                <span>Chat <p id="groupNameShow" style="font-size: 150%; color: white;">Khalid</p></span>
                                                            </div>

                                                        <div class="video_cam" style="position: absolute;left: 30%;">
                                                            <span>
                                                                <a id="groupAddMember" href="#" style="font-size: 200%;">
                                                                    <i class="fas fa-plus-square" style="color: white"></i>
                                                                </a>
                                                            </span>
                                                            <div id="popUpAddMember" class="pop_up_add_member">
                                                                <p class="pop_up_note">Add a new member</p>
                                                            </div>
                                                        </div>
                                                        <div class="username_info">
                                                            <span><%=firstname +" "+ lastname%></span>
                                                        </div>                                              
                                                    </div>
                                                    <span id="action_menu_btn"><i class="fas fa-ellipsis-v"></i></span>
                                                    <div class="action_menu">
                                                            <ul>
                                                                    <li><i class="fas fa-user-circle"></i> View profile</li>
                                                                    <li><i class="fas fa-users"></i> Add to close friends</li>
                                                                    <li><i class="fas fa-plus"></i> Add to group</li>
                                                                    <li><i class="fas fa-ban"></i> Block</li>
                                                            </ul>
                                                    </div>
                                            </div>

                                            <div id="chatFormGroup" class="card-body msg_card_body" style="/*background-image: url('images/hoabingan.jpg');*/ 
                                                 background-size: cover; overflow: auto;" >


                                            </div>

                                            <div class="card-footer">
                                                    <div class="input-group">
                                                        <div class="input-group-append">
                                                                <a href="" onclick="document.getElementById('imageInputGroup').click(); return false">
                                                                    <i class="fas fa-paperclip"></i>
                                                                </a>    
                                                        </div>
                                                        <div id="imageSendGroup" class="imageSend hiddenForm">
                                                            <img id="imageGroup" class="image" src="#"/>
                                                        </div>
                                                        <input type='file' accept="image/*" id="imageInputGroup" style="display: none;"/>
                                                        <textarea id="messageGroup" name="" class="form-control type_msg" placeholder="Type your message..."></textarea>
                                                        <div class="input-group-append">
                                                            <button id="submitMessageGroup" class="btn btn-primary mb-2" style="height: 100%">                                                          
                                                                <i class="fas fa-location-arrow"></i>				
                                                            </button>
                                                        </div>
                                                    </div>
                                            </div>
                                    </div>                        
                            </div>

                                <!-- ADD FORM-->                                
                                <div id="addForm" class="container addForm hiddenForm">
                                    <div style="margin-top: 25px;">
                                   
                                        <div class="form-group mb-2 ">
                                            <select id="addType">
                                                <option>Add a friend</option>
                                                <option>New Group</option>
                                            </select>
                                        </div>
                                        <div>
                                            <input id="addName" type="text" style="border-radius: 5px" placeholder="Enter Name">
                                        </div>
                                        <div class="inner"><button id="addSubmit" class="btn btn-primary mb-2" style="margin-top:10px;">Confirm</button></div>
                                        <div class="inner"><button id="addCancel" class="btn btn-primary mb-2" style="margin-top:10px;">Cancel</button></div>
                                    </div>
                                </div>      
			  
                        </div>
		</div>
        <span id="addResult"></span>
        <span id="Result"></span>           
        <script language="javascript" src="./js/checkFriendRequest.js"></script>       
        <script language="javascript" src="./js/add.js"></script>
        <script language="javascript" src="./js/imageSend.js"></script>
        <script language="javascript" src="./js/popUp.js"></script>
	</body>
</html>
