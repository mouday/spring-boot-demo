/**
 * 工具方法
 */
var utils = {
  // utils.getUUID
  getUUID: function () {
    var tempUrl = URL.createObjectURL(new Blob());
    var uuid = tempUrl.toString();
    //释放这个url
    URL.revokeObjectURL(tempUrl);
    return uuid.substring(uuid.lastIndexOf("/") + 1);
  },
};
