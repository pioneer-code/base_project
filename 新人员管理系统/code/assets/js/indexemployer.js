$(function() {
  // 调用 getEmployerInfo 获取管理员基本信息
  getEmployerInfo()

  var layer = layui.layer

  // 点击按钮，实现退出功能
  $('#btnLogout').on('click', function() {
    // 提示用户是否确认退出
    layer.confirm('确定退出登录?', { icon: 3, title: '提示' }, function(index) {
      // 1. 清空本地存储中的 token\company_name
      localStorage.removeItem('token')
      localStorage.removeItem('company_name')
      // 2. 重新跳转到登录页面
      location.href = './index.html'

      // 关闭 confirm 询问框
      layer.close(index)
    })
  })
  // 获取用户的基本信息
  function getEmployerInfo() {
    var company_name = localStorage.getItem('company_name')
    $('#welcome').html('欢迎&nbsp;&nbsp;' + company_name)
    $('.layui-nav-img').hide()
    if (company_name !== null) {
      var first = company_name[0].toUpperCase()
      $('.text-avatar')
        .html(first)
        .show()
    }
  }
})

