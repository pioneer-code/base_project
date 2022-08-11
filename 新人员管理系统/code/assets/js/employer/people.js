$(function () {
  var layer = layui.layer
  var form = layui.form

  initArtCateList(0)

  // 获取文章分类的列表
  function initArtCateList (num) {
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/employer/staff/select_employ?company_name=软件开发科技有限公司&employ='+num,
      success: function (res) {
        var htmlStr = template('tpl-table', res)
        $('tbody').html(htmlStr)
      }
    })
  }

  //切换状态功能
  var num = 0
  $('#btnReCate').on('click',function () {
    if(num == 0){
      num = 1
      initArtCateList(num)
      alert('这是状态为未录用（0）的员工')
    }else if(num == 1){
      num = 2
      initArtCateList(num)
      alert('这是状态为已录用（1）的员工')
    }else{
      num = 0
      initArtCateList(num)
      alert('这是状态为实习期（2）的员工')
    }
  })

})
