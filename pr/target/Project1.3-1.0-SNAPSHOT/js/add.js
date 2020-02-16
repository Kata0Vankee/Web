$(document).ready(function(){
    $('#add').click(function(){
        $('#addForm').removeClass('hiddenForm');
        $('#addCancel').click(function(){
            $('#addForm').addClass('hiddenForm');
        });
        $('#addSubmit').click(function(){
            var type = document.getElementById('addType').value;
            var name = $('#addName').val();
            console.log('add '+name);
            if (type === 'Add a friend') {
                function add(){
                    $.ajax({
                    url : 'AddFriendProcess',
                    type : 'post',
                    data : {friendInf : name, id : id},
                    success : function(data){
                    $('#addResult').html(data);
                    $('#addName').val('');
                    }
                    })
                };
                add();
            } else {
                $.ajax({
                url:'CreateGroup',
                type:'POST',
                data:{id:id, groupName:name},
                success : function(data){
                    $('#addResult').html(data);
                    $('#addName').val('');
                }
            })
            }
        })
    })
    $('#groupAddMember').click(function(){
        var newMemberName = prompt('Type member name');
        $.ajax({
            url : 'AddMember',
            type : 'post',
            data : {senderId : id, memberInfo : newMemberName, groupName : thisGroupName},
            success : function(data){
                $('#Result').html(data);
            }
        })
    })
})