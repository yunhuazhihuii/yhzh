<template>
	<el-row class="container" :span="24">
		<el-col :span="24" class="main">
			<aside >
				<!--导航菜单-->
				<el-menu  class="el-menu-vertical-demo">
					  <div v-for="(friend,index) in friendName">
              <div class="listFriend" >
                 <!-- 群组聊天 -->
                  <div @click="tabRoom(index)" ref="listClass" v-if="friend.group">
                     <el-badge :value="1" v-if="friend.newTip"></el-badge>
                    {{friend.username}}
                 </div>
                  <!-- 私人聊天 -->
                <div @click="tabRoom(index)" ref="listClass" v-if="friend.group == false">
                   <el-badge :value="1" v-if="friend.newTip"></el-badge>
                  {{friend.username}}
               </div>
            </div>

            </div>
				</el-menu>
			</aside>
<div style="width:80%;height: 100%;position:relative">
	<transition name="slide-left">
    <div class="chatting" v-for="room of currentRoom">

      <!-- 聊天界面头部 -->
      <div class="chatting-header">
        <div class="chatting-title">
          <h2>{{room.username}}</h2>
        </div>
        <div class="chatting-menu">
          <i @click="show2 = !show2" class="el-icon-more-outline" style="font-size: 30px"></i>
        </div>
         <transition name="el-zoom-in-top">
            <div v-show="show2" class="transition-box">
              <div v-for="name of room.chatName">
                <span style="color: #4c4c4c">{{name}}</span>
              </div>
            </div>
         </transition>
      </div>
      
      <!-- 聊天内容区域 -->
      <div @click.stop.prevent="isShowEmoji=false" ref="chattingContent" 
      class="chatting-content">

        <div v-for="item of room.msgs" style="height:80px">
          <div v-if="item.self" class="chatting-item self clearfix">
            <div class="msg-date">
              {{ item.date }}
            </div>
            <div class="msg-from">
              <span class="loc">{{item.loc}}</span>
              <span class="msg-author">{{item.from}}</span>
              <img :src="item.avatarUrl" alt="">
            </div>
            <div class="msg-content">{{item.content }}</div>
          </div>

           <div v-else class="chatting-item other clearfix">
            <div class="msg-date">
              {{ item.date }}
            </div>
            <div class="msg-from">
              <img :src="item.avatarUrl" alt="">
              <span class="loc">{{item.loc}}</span>
              <span class="msg-author">{{item.from}}</span>
            </div>
            <div class="msg-content">{{ item.content }}</div>
          </div>

        </div>

      </div>

      <!-- 输入区域 -->
      <div class="chatting-input" ref="textareaBox">

        <transition name="slide-bottom">
          <div v-show="isShowEmoji" class="emoji-display">
            <ul>
              <li @click="insertText(item)" v-for="item of emojis">{{item}}</li>
            </ul>
          </div>
        </transition>


        <div class="emoji">
          <i @click="showEmoji(isShowEmoji=!isShowEmoji);" class="icon-emoji"></i>
        </div>
        <textarea @blur="setbBlur" @focus="setFocus" @keyup.enter="send" @input="newLine" ref="textarea" v-model.trim="inputContent" placeholder="发送消息">
        </textarea>
        <button @click="send">发送</button>
      </div>

    </div>
  </transition>
 </div>
		</el-col>
	</el-row>
</template>

<script>
import {mapState} from 'vuex'
import {setStore, getStore,getSession} from '../common/js/util'
	export default {
  data() {
    return {
      show2:false,
      currentIndex:0,        
      //聊天内容可视高度
      height:0,
      currentRoom:[{username:'',msgs:  []}],
      inputContent: '',
      oContent: {},
      oTextarea: {},
      emojis: ['😂', '🙏', '😄', '😏', '😇', '😅', '😌', '😘', '😍',  '😜', '😎', '😊', '😳' , '😱', '😒', '😔', '😷', '👿',  '😩', '😤', '😣', '😰',  '😭', '👻', '👍', '👉', '👀', '🐶', '🐷', '😹',  '🔥', '🌈', '🍏', '⚽️', '❤️'],
      isShowEmoji: false,
    }
  },
  created(){
      //本地获取聊天记录
       this.msgs= JSON.parse(getStore('msgs_group'))||[];
  },
  watch: {
    msgs() {
      //本地存储聊天信息
      setStore('msgs_group', this.friendName);
    },
  },
  computed: {
    ...mapState([
      'name','avatarUrl','addr','friendName','socket','classStyle'
    ])
  },
  mounted() {
    var that=this;
      //获取聊天内容对象
    this.oContent = document.querySelector('.chatting-content');
    //设置高度
    this.height=document.documentElement.clientHeight
    this.oContent.style.height=(this.height-120)+'px'
    //获取输入框对象
    this.oTextarea = document.querySelector('textarea');

    
  },
  methods: {
    //切换聊天室
    tabRoom(index){
      //先清空
      this.currentRoom=[];
      this.friendName[index].newTip=false;
      //设置当前聊天页面
      this.currentRoom.push(this.friendName[index]);
      //清除上个样式样式
      this.$refs.listClass[this.currentIndex].style.background='#eef1f6';
      this.currentIndex=index;
      //绑定样式
      this.$refs.listClass[index].style.background='#cecece';
    },
    setbBlur(){
       const oTextareaBox = this.$refs.textareaBox[0];
       const oTextarea = this.$refs.textarea[0];
       oTextareaBox.style.background='#f5f5f5';
       oTextarea.style.background='#f5f5f5';
    },
    setFocus(){
       const oTextareaBox = this.$refs.textareaBox[0];
       const oTextarea = this.$refs.textarea[0];
       oTextareaBox.style.background='#fff';
       oTextarea.style.background='#fff';
    },
    showUserList(){
      
    },
    send() {
      this.isShowEmoji = false;
      if (this.inputContent === '') return;
        this.socket.emit('send private message', {
          addresser:this.name,
          recipient:this.currentRoom[0].username,
          date: this.moment().format('YYYY-MM-DD HH:mm:ss'),
          loc: this.addr,
          from: this.name,
          content: this.inputContent,
          avatarUrl: this.avatarUrl,
        });
        // 追加数据到数组
        this.currentRoom[0].msgs.push({
          date: this.moment().format('YYYY-MM-DD HH:mm:ss'),
          from: this.name,
          loc: this.addr,
          content: this.inputContent,
          self: true,
          avatarUrl: this.avatarUrl
        });
        this.inputContent = '';
      this.newLine();
    },
    showEmoji(flag) {
      this.isShowEmoji = flag;
    },
    // 发送表情方法
    insertText(str) {
      str = str + ` `;
      const oTextarea = this.$refs.textarea[0];
      if (document.selection) {
        let sel = document.selection.createRange();
        sel.text = str;
      } else if (typeof oTextarea.selectionStart === 'number' && typeof oTextarea.selectionEnd ==='number') {
        let startPos = oTextarea.selectionStart;
        let endPos = oTextarea.selectionEnd;
        let cursorPos = startPos;
        let tempVal = oTextarea.value;
        this.inputContent = tempVal.substring(0, startPos) + str + tempVal.substring(startPos, tempVal.length)
        cursorPos += str.length;
        oTextarea.selectionStart = oTextarea.selectionEnd = cursorPos;
      } else {
        oTextarea.value += str;
      }
      this.newLine();
    },
    newLine() {
      setTimeout(() => this.oContent.scrollTop = this.oContent.scrollHeight , 0);
    }
  }
}

</script>

<style scoped lang="scss">
	@import '~scss_vars';
	.container {
		position: absolute;
		top: 0px;
		bottom: 0px;
		width: 100%;
		.main {
			display: flex;
			// background: #324057;
			position: absolute;
			top: 0;
			bottom: 0px;
			overflow: hidden;
			aside {
				flex:0 0 230px;
				width: 230px;
				// position: absolute;
				// top: 0px;
				// bottom: 0px;
				.el-menu{
					height: 100%;
					.el-menu-item{
						background-color: #d1dbe5;
					}
				}
				.collapsed{
					width:60px;
					.item{
						position: relative;
					}
					.submenu{
						position:absolute;
						top:0px;
						left:60px;
						z-index:99999;
						height:auto;
						display:none;
					}

				}
			}
			.menu-collapsed{
				flex:0 0 60px;
				width: 60px;
			}
			.menu-expanded{
				flex:0 0 230px;
				width: 230px;
			}
			.content-container {
				flex:1;
				overflow-y: scroll;
				padding: 20px;
				.breadcrumb-container {
					//margin-bottom: 15px;
					.title {
						width: 200px;
						float: left;
						color: #475669;
					}
					.breadcrumb-inner {
						float: right;
					}
				}
				.content-wrapper {
					background-color: #fff;
					box-sizing: border-box;
				}
			}
		}
	}
	i{
  display: block;
  height: 30px;
}
  .chatting {
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100%;
     background:#f5f5f5;
    .chatting-header {
      position: absolute;
      top: 0;
      left: 0;
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 50px;
      width: 100%;
      background-color: #007fff;;
      color: white;
      .chatting-title {
        margin-left:18px;
      }
      .chatting-menu {
        width: 30px;
        height: 30px;
        margin-right: 10px;
        cursor: pointer;
        i.icon-menu {
          width: 50px;
          font-size: 0.32rem;
        }
      }
    }
    .chatting-content {
      flex: 1;
      width: 94%;
      background-color: #f5f5f5;
      overflow: auto;
      padding: 10px 0;
      margin: 0 auto;
      padding-bottom: 40px;
      padding-top: 50px;
      margin:0 auto;
      .online{
      	text-align: center;
        margin:12px 0;
      }
      .chatting-item {
        padding: 10px 0;
        width: 94%;
        margin:0 auto;
        .msg-date {
          text-align: center;
          color: gray;
          font-size: 80%;
        }
        .msg-from {
          display: flex;
          align-items: center;
          span.loc {
            color: gray;
            font-size: 60%;
            margin-right: 5px;
          }
          .msg-author {
            font-size: 0.34rem;
          }
          img {
            width: 30px;
            height: 30px;
            border-radius: 15px;
          }
        }
        .msg-content {
          margin-top: 5px;
          background-color: white;
          padding: 6px 10px;
          border-radius: 5px;
        }
      }
      .chatting-item + .chatting-item {
        margin-top: 10px;
      }
      .self {
        .msg-from {
          display: flex;
          justify-content: flex-end;
          align-items: center;
          img {
            margin-left: 10px;
          }
        }
        .msg-content {
          float: right;
          word-wrap: break-word;
          word-break: break-all;
          margin-right: 35px;
        }
      }
      .other {
        .msg-from {
          display: flex;
          justify-content: flex-start;
          align-items: center;
          span.loc {
            color: gray;
            font-size: 60%;
            margin-right: 5px;
          }
          img {
            margin-right: 10px;
          }
        }
        .msg-content {
          float: left;
          margin-left: 35px;
          word-wrap: break-word;
          word-break: break-all;
        }
      }
    }
    .chatting-input {
      position: relative;
      display: flex;
      height: 140px;
      width: 100%;
      bottom: 0;
      left: 0;
      flex-direction: column;
      border-top: 1px solid #eaeaea;
      .emoji-display {
        position: absolute;
        width: 50%;
        height: 210px;
        background-color: white;
        top: -210px;
        left: 0;
          overflow-y: auto;
          box-shadow: 4px 4px 4px #c3c0c0;
        ul {
          display: flex;
          flex-wrap: wrap;
          li {
            padding: 2px 3px;
            font-size: 1.2rem;
          }
        }
      }
      .emoji {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 60px;
        height: 40px;
        .icon-emoji {
          width: 30px;
          background: url('../icons/icon-emoji.svg') no-repeat;
          background-size: contain;
        }
      }
      textarea {
        height: 100px;
        font-size: 0.32rem;
        border: 0;
        background-color: #f5f5f5;
        text-indent: 12px;
        outline:none;
      }
      button {
        position:absolute;
        bottom: 8px;
        right:8px;
        width: 70px;
        height: 35px;
        border: 0;
        background-color: #007fff;
        color: white;
        font-size: 16px;
      }
    }
  }
  .listFriend{
    width: 100%;
    text-align: center;
    height: 46px;
    line-height: 46px;
  }
  .listFriend:hover{
    background:#eaeaea;
  }
  .transition-box {
   width: 100%;
    background: rgb(255, 255, 255);
    position: absolute;
    top: 40px;
    padding:12px; 
    border-bottom: 1px solid #ddd
  }
</style>