$(function() {
  initUserInfo1()
  var layer = layui.layer

  // 1.1 获取裁剪区域的 DOM 元素
  var $image = $('#image')
  // 1.2 配置选项
  const options = {
    // 纵横比
    aspectRatio: 1,
    // 指定预览区域
    preview: '.img-preview'
  }

  // 1.3 创建裁剪区域
  $image.cropper(options)

  // 为上传按钮绑定点击事件
  $('#btnChooseImage').on('click', function() {
    $('#file').click()
  })

  // 为文件选择框绑定 change 事件
  $('#file').on('change', function(e) {
    // 获取用户选择的文件
    var filelist = e.target.files
    if (filelist.length === 0) {
      return layer.msg('请选择照片！')
    }

    // 1. 拿到用户选择的文件
    var file = e.target.files[0]
    // 2. 将文件，转化为路径
    var imgURL = URL.createObjectURL(file)
    // 3. 重新初始化裁剪区域
    $image
      .cropper('destroy') // 销毁旧的裁剪区域
      .attr('src', imgURL) // 重新设置图片路径
      .cropper(options) // 重新初始化裁剪区域
  })

  // 为确定按钮，绑定点击事件
  $('#btnUpload').on('click', function() {
    // 1. 要拿到用户裁剪之后的头像
    var dataURL = $image
      .cropper('getCroppedCanvas', {
        // 创建一个 Canvas 画布
        width: 100,
        height: 100
      })
      .toDataURL('image/png') // 将 Canvas 画布上的内容，转化为 base64 格式的字符串
    // 2. 调用接口，把头像上传到服务器
    // $.ajax({
    //   method: 'POST',
    //   url: '/my/update/avatar',
    //   data: {
    //     avatar: dataURL
    //   },
    //   success: function(res) {
    //     if (res.status !== 0) {
    //       return layer.msg('更换头像失败！')
    //     }
    //     layer.msg('更换头像成功！')
    //     window.parent.getUserInfo()
    //   }
    // })
  })

 

  // 初始化用户的基本信息
  function initUserInfo1() {
    var url = localStorage.getItem('id_number')
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/employee/select_infomation/?id_number=' +url,
      dataType: "json",
      success: function(res) {
        $('.layui-form [name=idNumber]').val(res.staff.idNumber) 
        $('.layui-form [name=evaluation]').val(res.staff.evaluation) 
      }
    })
  }

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

  // 重置表单的数据
  $('#btnReset').on('click', function(e) {
    // 阻止表单的默认重置行为
    e.preventDefault()
    initUserInfo1()
  })
})
