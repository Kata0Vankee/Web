$(document).ready(function(){
    
    //////change image of user
    function changeImage(data) {
        $.ajax({
            url : 'ChangeAvatar',
            data : {avatar : data, id : id},
            type : 'post',
            success: alert('Change successfully!!!!')
        })
    }
    
    function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#userImage1').attr('src', e.target.result);
                    changeImage( e.target.result);
                    console.log( e.target.result);
                }
                reader.readAsDataURL(input.files[0]);                
            }
        }

    $("#userImageInput").change(function(){
        readURL(this);             
    });
    
    ///////////change image of group
    function changeAvatarGroup(data) {
        var groupName = document.getElementById('groupNameShow').innerHTML;
        console.log('group name change : '+ groupName);
        $.ajax({
            url : 'ChangeAvatarGroup',
            data : {avatar : data, groupName : groupName},
            type : 'post',
            success: alert('Change successfully!!!!')
        })
    }
    
    function readURLGroup(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#groupAvatar1').attr('src', e.target.result);
                    changeAvatarGroup( e.target.result);
                    console.log( e.target.result);
                }
                reader.readAsDataURL(input.files[0]);                
            }
        }

    $("#groupAvatarInput").change(function(){
        readURLGroup(this);             
    });
})