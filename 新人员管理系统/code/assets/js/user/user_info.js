$(function() {
  var form = layui.form
  var layer = layui.layer

  form.verify({
    nickname: function(value) {
      if (value.length > 6) {
        return '昵称长度必须在 1 ~ 6 个字符之间！'
      }
    }
  })

  initUserInfo()

  // 初始化用户的基本信息
  function initUserInfo() {
    var url = localStorage.getItem('id_number')
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/employee/select_infomation/?id_number=' +url,
      dataType: "json",
      success: function(res) {
        console.log('ok');
        console.log(res.staff);
        // if (res.update !== 1) {
        //   return layer.msg('获取用户信息失败！')
        // }
        // console.log(res)
        // 调用 form.val() 快速为表单赋值
        form.val('formUserInfo', res.staff)
      },
      error: function(res) {
        console.log(res);
      }
    })
  }

  // 重置表单的数据
  $('#btnReset').on('click', function(e) {
    // 阻止表单的默认重置行为
    e.preventDefault()
    initUserInfo()
  })

  // 监听表单的提交事件
  $('.layui-form').on('submit', function(e) {
    // 阻止表单的默认提交行为
    e.preventDefault()
    // 发起 ajax 数据请求
    var data = {
      id_number : $('.layui-form [name=idNumber]').val(),
      evaluation : $('.layui-form [name=evaluation]').val()
    }
    console.log(data);
    $.ajax({
      method: 'POST',
      url: 'http://119.23.241.76:8000/employee/update_evaluation',
      data: data,
      success: function(res) {
        console.log(res);
        if (res.update !== 1) {
          return layer.msg('更新用户个人评价失败！')
        }
        layer.msg('更新用户个人评价成功！')
        // 调用父页面中的方法，重新渲染用户的头像和用户的信息
        //window.parent.getUserInfo()
      }
    })
  })
})
