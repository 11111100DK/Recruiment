//js nút QL đổi màu
let btnQL = document.getElementsByClassName('QLdonhang');
let table = document.getElementsByClassName('table')
//mặc định mục chờ xác nhận là mục hiển thị đầu tiên
table[0].style.display="block"
btnQL[0].style.background="#2a2b2c"
btnQL[0].style.color="white"
            for(let i=1;i<btnQL.length;i++){
                btnQL[i].style.background="none"
                btnQL[i].style.color="black"
                table[i].style.display="none"
            }

//click mục nào đổi màu mục đó và hiển thị table tuong ứng
for(let i=0;i<btnQL.length;i++){

    btnQL[i].addEventListener('click',() =>{
        for(let i=0;i<btnQL.length;i++){
            btnQL[i].style.background="none"
            btnQL[i].style.color="black"
            table[i].style.display="none"
        }
        table[i].style.display="block"
        btnQL[i].style.background="#2a2b2c"
        btnQL[i].style.color="white"
    })
}

    function deleteItem(id){
        var itemId = id.getAttribute('data-id');
        Swal.fire({
                title: 'Do you want to delete?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes',
            }).then((result) => {
                if (result.isConfirmed){
                    window.location.href = '/admin/role/delete/' + itemId
                }
            })
    }


function warning(mess) {
        let icon = '';
        let title = '';
        let text = '';

        if (mess == "Name role is required") {
            icon = 'warning'
            title = 'Oosp. Fail!!!'
            text = "Name role is required"
        }else if(mess == "This role can not be deleted because it has been used by some account"){
            icon = 'warning'
            title = 'Oosp. Fail!!!'
            text = mess
        }else if(mess == "Name is exist"){
            icon = 'warning'
            title = 'Oosp. Fail!!!'
           text = mess
        }else if(mess == "success"){
            icon = 'success'
            title = 'Congratulation!!!!!'
            text = 'Success'
        }else if(mess == "Bạn không có quyền sử dụng chức năng này"){
            icon = 'warning'
            title = 'Oosp. Fail!!!'
            text = mess
        }

        if (icon != '' && title != '' && text != '')
            Swal.fire({
                icon: icon,
                title: title,
                text: text
            })
}

function banNick(id){
    var itemId = id.getAttribute('data-id');
    var page = id.getAttribute('data-page');
    var userName = id.getAttribute('data-userName');
    var data = {
        id : itemId, page: page, search: userName
    }
            Swal.fire({
                title: 'Do you want to change status?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes',
            }).then((result) => {
                if (result.isConfirmed){
                    window.location.href = '/admin/account/changeStatus?id=' + parseInt(itemId)+'&search='+userName+'&page='+parseInt(page)
//                    $.ajax({
//                            type: "POST", // hoặc "GET" tùy thuộc vào phương thức bạn muốn sử dụng
//                            url: "/admin/account/changeStatus",
//                            contentType: "application/json",
//                            data: JSON.stringify(data),
//                            success: function(response) {
//                                //window.location.href = "/admin/accountUser?useName="+userName+"?page="+page
//                            },
//                            error: function(error) {
//                                console.error(error);
//                            }
//                        });
                }
            })
}

function banNickAdmin(id){
    var itemId = id.getAttribute('data-id');
    var page = id.getAttribute('data-page');
    var userName = id.getAttribute('data-userName');
    var data = {
        id : itemId, page: page, search: userName
    }
            Swal.fire({
                title: 'Do you want to change status?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes',
            }).then((result) => {
                if (result.isConfirmed){
                    window.location.href = '/admin/account/changeStatusAdmin?id=' + parseInt(itemId)+'&search='+userName+'&page='+parseInt(page)
                }
            })
}


