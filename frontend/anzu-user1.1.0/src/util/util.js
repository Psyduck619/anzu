function getDuration(my_time) {
  var days = my_time / 1000 / 60 / 60 / 24
  var daysRound = Math.floor(days)
  var hours = my_time / 1000 / 60 / 60 - 24 * daysRound
  var hoursRound = Math.floor(hours)
  var minutes = my_time / 1000 / 60 - 24 * 60 * daysRound - 60 * hoursRound
  var minutesRound = Math.floor(minutes)
  var seconds =
    my_time / 1000 -
    24 * 60 * 60 * daysRound -
    60 * 60 * hoursRound -
    60 * minutesRound
  console.log(
    '转换时间:',
    daysRound + '天',
    hoursRound + '时',
    minutesRound + '分',
    seconds + '秒'
  )
  var time = hoursRound + ':' + minutesRound + ':' + seconds
  return days
}
function timestampToTime(timestamp) {
  var date = new Date(timestamp * 1000) //时间戳为10位需*1000，时间戳为13位的话不需乘1000
  Y = date.getFullYear() + '-'
  M =
    (date.getMonth() + 1 < 10
      ? '0' + (date.getMonth() + 1)
      : date.getMonth() + 1) + '-'
  D = date.getDate() + ' '
  h = date.getHours() + ':'
  m = date.getMinutes() + ':'
  s = date.getSeconds()
  return Y + M + D + h + m + s
}
