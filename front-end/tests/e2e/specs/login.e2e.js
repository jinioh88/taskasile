
module.exports = {
  'login test': function (browser) {
    browser
      .url(proccess.env.VUE_DEV_SERVER_URL + 'login')
      .waitForElementVisible('#app', 50000)
      .assert.containsText('h1', 'TaskAgile').end()
  }
}
