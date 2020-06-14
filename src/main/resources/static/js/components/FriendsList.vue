<template>
<div v-if="friendList" class="container-fluid panel__box">
	<div class="row p-2 align-items-center px-4 activated">
		<a class="d-flex align-items-center">
			<img src="/images/tool.png" height="20px" alt />
			<input type="text" v-model="search" placeholder="Поиск" class="friends border ml-2 px-2 py-1" />
		</a>
		<select v-model="sel" class="browser-default friend ml-2 custom-select">
			<option value="0" selected>По имени</option>
			<option value="1">По школе</option>
		</select>
		<a class="d-flex align-items-center" v-if="sel==1">
			<input type="date" placeholder="Дата окончания" class="friends w-100 border ml-2 px-2 py-1" />
		</a>
		<a class="d-flex align-items-center" v-if="sel==1">
			<input type="date" placeholder="Дата окончания" class="friends w-100 border ml-2 px-2 py-1" />
		</a>
		<a class></a>
		<!-- <a class="avatar"></a> -->
	</div>
	<div class="row white mess__pole pb-1 p-2 flex-row scrollbar-deep-blue" :style="'max-height:'+heightPole">
		<div v-for="(friend, index) in getFriend" :key="friend.id" class="col-12 px-4">
			<div>
				<div class="d-flex">
					<a :href="'/#/'+friend.id" class="avatar" :style="'background: url('+friend.about.avatar+');  background-position: center;  background-size: cover;'"></a>
					<div class="col">
						<p class="name m-0">{{friend.name}} {{friend.famile}}</p>
						<p class="mb-1" v-if="friend.about.date">
							Дата рждения:
							<span class="ml-2">{{friend.about.date}} г.</span>
						</p>
						<p class="mb-1" v-if="friend.about.scholl">
							Школа:
							<span class="ml-2">{{friend.about.scholl}}</span>
						</p>
					</div>
					<div class="col text-right">

						<div class="btn-group" v-if="param!='subs' && param != 'alluser'" role="group" aria-label="Basic example">
							<button type="button" class="btn-two py-2 px-3 btn btn-sm" @click="sendCom(friend.id, 'bestFriend')" name="button">Лучшие</button>
							<button type="button" class="btn-two py-2 px-3 btn btn-sm" @click="sendCom(friend.id, 'relatives')" name="button">Родственники</button>
							<button type="button" class="btn-two py-2 px-3 btn btn-sm" @click="sendCom(friend.id, 'colleag')" name="button">Коллеги</button>
							<button type="button" class="btn-two py-2 px-3 btn btn-sm" @click="sendCom(friend.id, 'friend')" name="button"><b>X</b></button>
						</div>


						<span v-if="friend.status == 'bestFriend'" class="fan">Лучшие друзья</span>
					</div>
				</div>
				{{friendList.length}}
				<hr class="mt-2" v-if="index != count" />
			</div>
		</div>
		<div v-if="Object.keys(friendList).length == 0" class="text-center mx-auto">Список пуст</div>
	</div>
</div>
</template>


<script>
export default {
	components: {},
	props: ["friendList", "getFriends", "param"],
	mounted() {
		this.heightPole = document.body.clientHeight - 200 + "px";
	},
	data() {
		return {
			heightPole: "200px",
			count: Object.keys(this.friendList).length,
			sel: 0,
			ops: {},
			search: '',
		};
	},
	methods: {
		openbut(ind) {
			this.ops[ind] = true
		},
		sendCom(userid, status) {
			axios.post("/setstatus", {
				iduser: userid,
				status: status
			}).then(res => {
				this.getFriends()
				// console.log(res.data);
			})
		},
	},
	computed: {
		getFriend() {
			let arrfriend = {};
			if (!this.search)
				return this.friendList;

				for (var i = 0; i < Object.keys(this.friendList).length; i++) {
					if ((this.friendList[i].name.indexOf(this.search) > -1) || (this.friendList[i].famile.indexOf(this.search) > -1)) {
						arrfriend[i] = this.friendList[i]
					}
				}
				return arrfriend
		}
	},
	watch: {
		friendList(value) {
			this.count = Object.keys(value).length;
		}
	}
};
</script>


<style media="screen" scoped>
.btn-two {
	font-size: 10px;
	background: #fff;
	box-shadow: none;
	/* box-shadow: -4px -4px 10px rgba(255, 255, 255, 0.5), 2px 2px 10px rgba(163, 177, 198, 0.5); */
	border-radius: 5px;
	border: 1px solid #ece4e4;
}

.fan {
	color: #4f5662;
	font-size: 15px;
	font-weight: normal;
}

.friend {
	width: 100px;
	height: 30px;
	font-size: 12px;
	color: rgba(79, 86, 98, 0.7);
	border-radius: 5px;
}

.friends {
	width: 255px;
	font-size: 12px;
	color: rgba(79, 86, 98, 0.7);
	border-radius: 5px;
}

.mess__pole {
	overflow-y: auto;
	padding: 20px 0;
	border-top: 2px solid rgba(163, 177, 198, 0.5);
}

.max {
	font-size: 19px;
}

.activated {
	border-radius: 5px;
	background: #f4f4f4;
}

.avatar {
	height: 70px;
	width: 70px;
	border-radius: 50px;
}

p {
	color: #4f5662;
	font-size: 14px;
	font-weight: normal;
}

hr {
	/* width: 330px; */
	border: 1.5px solid rgba(163, 177, 198, 0.18);
}

p>span {
	/* line-height: 10px; */
	/* font-size: 30px; */
	color: #000;
	font-weight: normal;
}

.name {
	font-weight: 500 !important;
	/* color: #00bcd4 !important; */
	font-size: 17px;
	font-weight: normal;
}

.pl-44 {
	padding-left: 35px;
}

.panel__box {
	margin-top: 21px;
	background: #f4f4f4;
	box-shadow: -4px -4px 10px rgba(255, 255, 255, 0.5),
		2px 2px 10px rgba(163, 177, 198, 0.5);
	border-radius: 5px;
}
</style>
