///////////set avatar user
function setImage(data) {
        console.log('avatar user : '+data.toString().slice(0,10));
        $('#userImage1').attr('src',data.toString());
        $('#userImage2').attr('src',data.toString());
}
function setImageDefault(){
    $('#userImage1').attr('src',$('#userImageDefault').attr('src'));
    $('#userImage2').attr('src',$('#userImageDefault').attr('src'));
    $.ajax({
        url : 'ChangeAvatar',
        data : {avatar : $('#userImageDefault').attr('src'), id:id},
        type : 'post'
    })
    console.log("da set avatar default");
}

////////set avatar group
function setGroupAvatarDefault(){
    $('#userImage1').attr('src',$('#userImageDefault').attr('src'));
    $.ajax({
        url : 'ChangeAvatar',
        data : {avatar : $('#userImageDefault').attr('src'), id:id},
        type : 'post'
    })
    console.log("da set avatar default");
}


function loadUserImage(){
    $.ajax({
        url : 'LoadUserImage',
        type : 'post',
        data: {id:id},
        success : setImage,
        error : setImageDefault
    })
}

connect();
loadUserImage();
