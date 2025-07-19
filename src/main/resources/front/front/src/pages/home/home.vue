<template>
	<div class="home-preview">




		<!-- 新闻资讯 -->
		<div id="animate_newsnews" class="news animate__animated">
			<div class="news_title_box">
				<span class="news_title">汽车资讯</span>
				<span class="news_subhead">{{'news'.toUpperCase()}}</span>
			</div>
			<!-- 样式五 -->
			<div v-if="newsList.length" class="list list5 index-pv1">
				<div @click="toDetail('newsDetail', newsList[0])" v-if="newsList.length>0" class="list-item animation-box">
					<img :src="baseUrl + newsList[0].picture" alt="">
					<div class="infoBox">
						<div class="name line1">{{newsList[0].title}}</div>
						<div class="desc line3">{{newsList[0].introduction}}</div>
						<div class="time_item">
							<span class="icon iconfont icon-shijian21"></span>
							<span class="label">发布时间：</span>
							<span class="text">{{newsList[0].addtime.split(' ')[0]}}</span>
						</div>
						<div class="publisher_item">
							<span class="icon iconfont icon-geren16"></span>
							<span class="label">发布人：</span>
							<span class="text">{{newsList[0].name}}</span>
						</div>
						<div class="like_item">
							<span class="icon iconfont icon-zan10"></span>
							<span class="label">点赞：</span>
							<span class="text">{{newsList[0].thumbsupnum}}</span>
						</div>
						<div class="collect_item">
							<span class="icon iconfont icon-shoucang10"></span>
							<span class="label">收藏：</span>
							<span class="text">{{newsList[0].storeupnum}}</span>
						</div>
						<div class="view_item">
							<span class="icon iconfont icon-chakan2"></span>
							<span class="label">点击量：</span>
							<span class="text">{{newsList[0].clicknum}}</span>
						</div>
					</div>
				</div>
				<div class="list-body">
					<div @click="toDetail('newsDetail', newsList[1])" v-if="newsList.length>1" class="list-item1 animation-box">
						<div class="name line1">{{newsList[1].title}}</div>
						<div class="desc line3">{{newsList[1].introduction}}</div>
						<div class="time_item">
							<span class="icon iconfont icon-shijian21"></span>
							<span class="label">发布时间：</span>
							<span class="text">{{newsList[1].addtime.split(' ')[0]}}</span>
						</div>
						<div class="publisher_item">
							<span class="icon iconfont icon-geren16"></span>
							<span class="label">发布人：</span>
							<span class="text">{{newsList[1].name}}</span>
						</div>
						<div class="like_item">
							<span class="icon iconfont icon-zan10"></span>
							<span class="label">点赞：</span>
							<span class="text">{{newsList[1].thumbsupnum}}</span>
						</div>
						<div class="collect_item">
							<span class="icon iconfont icon-shoucang10"></span>
							<span class="label">收藏：</span>
							<span class="text">{{newsList[1].storeupnum}}</span>
						</div>
						<div class="view_item">
							<span class="icon iconfont icon-chakan2"></span>
							<span class="label">浏览次数：</span>
							<span class="text">{{newsList[1].clicknum}}</span>
						</div>
					</div>
					<div @click="toDetail('newsDetail', newsList[2])" v-if="newsList.length>2" class="list-item2 animation-box">
						<div class="name line1">{{newsList[2].title}}</div>
						<div class="desc line3">{{newsList[2].introduction}}</div>
						<div class="time_item">
							<span class="icon iconfont icon-shijian21"></span>
							<span class="label">发布时间：</span>
							<span class="text">{{newsList[2].addtime.split(' ')[0]}}</span>
						</div>
						<div class="publisher_item">
							<span class="icon iconfont icon-geren16"></span>
							<span class="label">发布人：</span>
							<span class="text">{{newsList[2].name}}</span>
						</div>
						<div class="like_item">
							<span class="icon iconfont icon-zan10"></span>
							<span class="label">点赞：</span>
							<span class="text">{{newsList[2].thumbsupnum}}</span>
						</div>
						<div class="collect_item">
							<span class="icon iconfont icon-shoucang10"></span>
							<span class="label">收藏：</span>
							<span class="text">{{newsList[2].storeupnum}}</span>
						</div>
						<div class="view_item">
							<span class="icon iconfont icon-chakan2"></span>
							<span class="label">浏览次数：</span>
							<span class="text">{{newsList[2].clicknum}}</span>
						</div>
					</div>
					<div @click="toDetail('newsDetail', newsList[3])" v-if="newsList.length>3" class="list-item3 animation-box">
						<div class="name line1">{{newsList[3].title}}</div>
						<div class="desc line3">{{newsList[3].introduction}}</div>
						<div class="time_item">
							<span class="icon iconfont icon-shijian21"></span>
							<span class="label">发布时间：</span>
							<span class="text">{{newsList[3].addtime.split(' ')[0]}}</span>
						</div>
						<div class="publisher_item">
							<span class="icon iconfont icon-geren16"></span>
							<span class="label">发布人：</span>
							<span class="text">{{newsList[3].name}}</span>
						</div>
						<div class="like_item">
							<span class="icon iconfont icon-zan10"></span>
							<span class="label">点赞：</span>
							<span class="text">{{newsList[3].thumbsupnum}}</span>
						</div>
						<div class="collect_item">
							<span class="icon iconfont icon-shoucang10"></span>
							<span class="label">收藏：</span>
							<span class="text">{{newsList[3].storeupnum}}</span>
						</div>
						<div class="view_item">
							<span class="icon iconfont icon-chakan2"></span>
							<span class="label">浏览次数：</span>
							<span class="text">{{newsList[3].clicknum}}</span>
						</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('news')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 新闻资讯 -->
		<!-- Product推荐 -->
		<div id="animate_recommendxinnengyuanqiche" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">New Energy Vehicles推荐</span>
				<span class="recommend_subhead">{{'xinnengyuanqiche'.toUpperCase()}} RECOMMEND</span>
			</div>
			<div class="list list21 index-pv1">
				<div class="list-body-top">
					<div class="tab-title">New Energy Vehicles推荐</div>
					<div class="tab-right" @click="moreBtn('xinnengyuanqiche')">
						<span class="text">查看更多</span>
						<span class="icon iconfont icon-gengduo1"></span>
					</div>
				</div>
				<div class="list-body">
					<div v-if="index<Number(4)" class="list-item" v-for="(item,index) in xinnengyuanqicheRecommend" :key="index" @click="toDetail('xinnengyuanqicheDetail', item)">
						<div class="img">
							<img v-if="preHttp(item.tupian)" :src="item.tupian.split(',')[0]" alt="" />
							<img v-else :src="baseUrl + (item.tupian?item.tupian.split(',')[0]:'')" alt="" />
						</div>
						<div class="infoBox">
							<div class="name">{{item.qichemingcheng}}</div>
							<div class="bottomInfo">
								<div class="price">￥{{item.price}}</div>
								<div class="time_item">
									<span class="icon iconfont icon-shijian21"></span>
									<span class="label">发布时间：</span>
									<span class="text">{{item.addtime.split(' ')[0]}}</span>
								</div>
								<div class="like_item">
									<span class="icon iconfont icon-zan10"></span>
									<span class="label">点赞：</span>
									<span class="text">{{item.thumbsupnum}}</span>
								</div>
								<div class="collect_item">
									<span class="icon iconfont icon-shoucang10"></span>
									<span class="label">收藏：</span>
									<span class="text">{{item.storeupnum}}</span>
								</div>
								<div class="view_item">
									<span class="icon iconfont icon-liulan04"></span>
									<span class="label">浏览次数：</span>
									<span class="text">{{item.clicknum}}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('xinnengyuanqiche')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- Product推荐 -->
		<!-- 特价Product -->
		<div id="animate_listxinnengyuanqiche" class="lists animate__animated">
			<div class="list_title_box">
				<span class="list_title">New Energy Vehicles展示</span>
				<span class="list_subhead">{{'xinnengyuanqiche'.toUpperCase()}} SHOW</span>
			</div>
			<!-- 样式九 -->
			<div class="list list9 index-pv1">
				<div class="list-body-top">
					<div v-if="xinnengyuanqicheList.length>0" @click="toDetail('xinnengyuanqicheDetail', xinnengyuanqicheList[0])" class="list-item1 animation-box">
						<img v-if="preHttp(xinnengyuanqicheList[0].tupian)" :src="xinnengyuanqicheList[0].tupian.split(',')[0]" alt="" />
						<img v-else :src="baseUrl + (xinnengyuanqicheList[0].tupian?xinnengyuanqicheList[0].tupian.split(',')[0]:'')" alt="" />
						<div class="infoBox">
							<div class="name">{{xinnengyuanqicheList[0].qichemingcheng}}</div>
							<div class="price">￥{{xinnengyuanqicheList[0].price}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{xinnengyuanqicheList[0].addtime.split(' ')[0]}}</span>
							</div>
							<div class="like_item">
								<span class="icon iconfont icon-zan10"></span>
								<span class="label">点赞：</span>
								<span class="text">{{xinnengyuanqicheList[0].thumbsupnum}}</span>
							</div>
							<div class="collect_item">
								<span class="icon iconfont icon-shoucang10"></span>
								<span class="label">收藏：</span>
								<span class="text">{{xinnengyuanqicheList[0].storeupnum}}</span>
							</div>
							<div class="view_item">
								<span class="icon iconfont icon-chakan2"></span>
								<span class="label">浏览次数：</span>
								<span class="text">{{xinnengyuanqicheList[0].clicknum}}</span>
							</div>
						</div>
					</div>
					<div v-if="xinnengyuanqicheList.length>1" @click="toDetail('xinnengyuanqicheDetail', xinnengyuanqicheList[1])" class="list-item2 animation-box">
						<img v-if="preHttp(xinnengyuanqicheList[1].tupian)" :src="xinnengyuanqicheList[1].tupian.split(',')[0]" alt="" />
						<img v-else :src="baseUrl + (xinnengyuanqicheList[1].tupian?xinnengyuanqicheList[1].tupian.split(',')[0]:'')" alt="" />
						<div class="infoBox">
							<div class="name">{{xinnengyuanqicheList[1].qichemingcheng}}</div>
							<div class="price">￥{{xinnengyuanqicheList[1].price}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{xinnengyuanqicheList[1].addtime.split(' ')[0]}}</span>
							</div>
							<div class="like_item">
								<span class="icon iconfont icon-zan10"></span>
								<span class="label">点赞：</span>
								<span class="text">{{xinnengyuanqicheList[1].thumbsupnum}}</span>
							</div>
							<div class="collect_item">
								<span class="icon iconfont icon-shoucang10"></span>
								<span class="label">收藏：</span>
								<span class="text">{{xinnengyuanqicheList[1].storeupnum}}</span>
							</div>
							<div class="view_item">
								<span class="icon iconfont icon-chakan2"></span>
								<span class="label">浏览次数：</span>
								<span class="text">{{xinnengyuanqicheList[1].clicknum}}</span>
							</div>
						</div>
					</div>
				</div>
				<div class="list-body-bottom">
					<div v-if="xinnengyuanqicheList.length>2" @click="toDetail('xinnengyuanqicheDetail', xinnengyuanqicheList[2])" class="list-item1 animation-box">
						<img v-if="preHttp(xinnengyuanqicheList[2].tupian)" :src="xinnengyuanqicheList[2].tupian.split(',')[0]" alt="" />
						<img v-else :src="baseUrl +  (xinnengyuanqicheList[2].tupian?xinnengyuanqicheList[2].tupian.split(',')[0]:'')" alt="" />
						<div class="infoBox">
							<div class="name">{{xinnengyuanqicheList[2].qichemingcheng}}</div>
							<div class="price">￥{{xinnengyuanqicheList[2].price}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{xinnengyuanqicheList[2].addtime.split(' ')[0]}}</span>
							</div>
							<div class="like_item">
								<span class="icon iconfont icon-zan10"></span>
								<span class="label">点赞：</span>
								<span class="text">{{xinnengyuanqicheList[2].thumbsupnum}}</span>
							</div>
							<div class="collect_item">
								<span class="icon iconfont icon-shoucang10"></span>
								<span class="label">收藏：</span>
								<span class="text">{{xinnengyuanqicheList[2].storeupnum}}</span>
							</div>
							<div class="view_item">
								<span class="icon iconfont icon-chakan2"></span>
								<span class="label">浏览次数：</span>
								<span class="text">{{xinnengyuanqicheList[2].clicknum}}</span>
							</div>
						</div>
					</div>
					<div v-if="xinnengyuanqicheList.length>3" @click="toDetail('xinnengyuanqicheDetail', xinnengyuanqicheList[3])" class="list-item2 animation-box">
						<img v-if="preHttp(xinnengyuanqicheList[3].tupian)" :src="xinnengyuanqicheList[3].tupian.split(',')[0]" alt="" />
						<img v-else :src="baseUrl + (xinnengyuanqicheList[3].tupian?xinnengyuanqicheList[3].tupian.split(',')[0]:'')" alt="" />
						<div class="infoBox">
							<div class="name">{{xinnengyuanqicheList[3].qichemingcheng}}</div>
							<div class="price">￥{{xinnengyuanqicheList[3].price}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{xinnengyuanqicheList[3].addtime.split(' ')[0]}}</span>
							</div>
							<div class="like_item">
								<span class="icon iconfont icon-zan10"></span>
								<span class="label">点赞：</span>
								<span class="text">{{xinnengyuanqicheList[3].thumbsupnum}}</span>
							</div>
							<div class="collect_item">
								<span class="icon iconfont icon-shoucang10"></span>
								<span class="label">收藏：</span>
								<span class="text">{{xinnengyuanqicheList[3].storeupnum}}</span>
							</div>
							<div class="view_item">
								<span class="icon iconfont icon-chakan2"></span>
								<span class="label">浏览次数：</span>
								<span class="text">{{xinnengyuanqicheList[3].clicknum}}</span>
							</div>
						</div>
					</div>
					<div v-if="xinnengyuanqicheList.length>4" @click="toDetail('xinnengyuanqicheDetail', xinnengyuanqicheList[4])" class="list-item3 animation-box">
						<img v-if="preHttp(xinnengyuanqicheList[4].tupian)" :src="xinnengyuanqicheList[4].tupian.split(',')[0]" alt="" />
						<img v-else :src="baseUrl + (xinnengyuanqicheList[4].tupian?xinnengyuanqicheList[4].tupian.split(',')[0]:'')" alt="" />
						<div class="infoBox">
							<div class="name">{{xinnengyuanqicheList[4].qichemingcheng}}</div>
							<div class="price">￥{{xinnengyuanqicheList[4].price}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{xinnengyuanqicheList[4].addtime.split(' ')[0]}}</span>
							</div>
							<div class="like_item">
								<span class="icon iconfont icon-zan10"></span>
								<span class="label">点赞：</span>
								<span class="text">{{xinnengyuanqicheList[4].thumbsupnum}}</span>
							</div>
							<div class="collect_item">
								<span class="icon iconfont icon-shoucang10"></span>
								<span class="label">收藏：</span>
								<span class="text">{{xinnengyuanqicheList[4].storeupnum}}</span>
							</div>
							<div class="view_item">
								<span class="icon iconfont icon-chakan2"></span>
								<span class="label">浏览次数：</span>
								<span class="text">{{xinnengyuanqicheList[4].clicknum}}</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('xinnengyuanqiche')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
	

		</div>
		<!-- 特价Product -->
		<!-- 特价Product -->
		<div id="animate_listremenqiche" class="lists animate__animated">
			<div class="list_title_box">
				<span class="list_title">Popular Cars展示</span>
				<span class="list_subhead">{{'remenqiche'.toUpperCase()}} SHOW</span>
			</div>
			<!-- 样式九 -->
			<div class="list list9 index-pv1">
				<div class="list-body-top">
					<div v-if="remenqicheList.length>0" @click="toDetail('remenqicheDetail', remenqicheList[0])" class="list-item1 animation-box">
						<img v-if="preHttp(remenqicheList[0].tupian)" :src="remenqicheList[0].tupian.split(',')[0]" alt="" />
						<img v-else :src="baseUrl + (remenqicheList[0].tupian?remenqicheList[0].tupian.split(',')[0]:'')" alt="" />
						<div class="infoBox">
							<div class="name">{{remenqicheList[0].qichemingcheng}}</div>
							<div class="price">￥{{remenqicheList[0].price}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{remenqicheList[0].addtime.split(' ')[0]}}</span>
							</div>
							<div class="collect_item">
								<span class="icon iconfont icon-shoucang10"></span>
								<span class="label">收藏：</span>
								<span class="text">{{remenqicheList[0].storeupnum}}</span>
							</div>
						</div>
					</div>
					<div v-if="remenqicheList.length>1" @click="toDetail('remenqicheDetail', remenqicheList[1])" class="list-item2 animation-box">
						<img v-if="preHttp(remenqicheList[1].tupian)" :src="remenqicheList[1].tupian.split(',')[0]" alt="" />
						<img v-else :src="baseUrl + (remenqicheList[1].tupian?remenqicheList[1].tupian.split(',')[0]:'')" alt="" />
						<div class="infoBox">
							<div class="name">{{remenqicheList[1].qichemingcheng}}</div>
							<div class="price">￥{{remenqicheList[1].price}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{remenqicheList[1].addtime.split(' ')[0]}}</span>
							</div>
							<div class="collect_item">
								<span class="icon iconfont icon-shoucang10"></span>
								<span class="label">收藏：</span>
								<span class="text">{{remenqicheList[1].storeupnum}}</span>
							</div>
						</div>
					</div>
				</div>
				<div class="list-body-bottom">
					<div v-if="remenqicheList.length>2" @click="toDetail('remenqicheDetail', remenqicheList[2])" class="list-item1 animation-box">
						<img v-if="preHttp(remenqicheList[2].tupian)" :src="remenqicheList[2].tupian.split(',')[0]" alt="" />
						<img v-else :src="baseUrl +  (remenqicheList[2].tupian?remenqicheList[2].tupian.split(',')[0]:'')" alt="" />
						<div class="infoBox">
							<div class="name">{{remenqicheList[2].qichemingcheng}}</div>
							<div class="price">￥{{remenqicheList[2].price}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{remenqicheList[2].addtime.split(' ')[0]}}</span>
							</div>
							<div class="collect_item">
								<span class="icon iconfont icon-shoucang10"></span>
								<span class="label">收藏：</span>
								<span class="text">{{remenqicheList[2].storeupnum}}</span>
							</div>
						</div>
					</div>
					<div v-if="remenqicheList.length>3" @click="toDetail('remenqicheDetail', remenqicheList[3])" class="list-item2 animation-box">
						<img v-if="preHttp(remenqicheList[3].tupian)" :src="remenqicheList[3].tupian.split(',')[0]" alt="" />
						<img v-else :src="baseUrl + (remenqicheList[3].tupian?remenqicheList[3].tupian.split(',')[0]:'')" alt="" />
						<div class="infoBox">
							<div class="name">{{remenqicheList[3].qichemingcheng}}</div>
							<div class="price">￥{{remenqicheList[3].price}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{remenqicheList[3].addtime.split(' ')[0]}}</span>
							</div>
							<div class="collect_item">
								<span class="icon iconfont icon-shoucang10"></span>
								<span class="label">收藏：</span>
								<span class="text">{{remenqicheList[3].storeupnum}}</span>
							</div>
						</div>
					</div>
					<div v-if="remenqicheList.length>4" @click="toDetail('remenqicheDetail', remenqicheList[4])" class="list-item3 animation-box">
						<img v-if="preHttp(remenqicheList[4].tupian)" :src="remenqicheList[4].tupian.split(',')[0]" alt="" />
						<img v-else :src="baseUrl + (remenqicheList[4].tupian?remenqicheList[4].tupian.split(',')[0]:'')" alt="" />
						<div class="infoBox">
							<div class="name">{{remenqicheList[4].qichemingcheng}}</div>
							<div class="price">￥{{remenqicheList[4].price}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{remenqicheList[4].addtime.split(' ')[0]}}</span>
							</div>
							<div class="collect_item">
								<span class="icon iconfont icon-shoucang10"></span>
								<span class="label">收藏：</span>
								<span class="text">{{remenqicheList[4].storeupnum}}</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('remenqiche')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
	

		</div>
		<!-- 特价Product -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				newsList: [],
				xinnengyuanqicheRecommend: [],

				xinnengyuanqicheList: [],
				remenqicheList: [],


				recommendIndex21xinnengyuanqiche: 0,
				recommendList21xinnengyuanqiche: [],
				recommendColumn21xinnengyuanqiche: '',


			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getNewsList();
			this.getList();
			this.xinnengyuanqicheRecommendCate()
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		//方法集合
		methods: {
			swiperChanges() {
				setTimeout(()=>{
				},750)
			},
			xinnengyuanqicheRecommendCate(){
				this.$http.get('qichefenlei/list',{
					params: {
						limit: Number(6)
					}
				}).then(res => {
					if (res.data.code == 0) {
						this.recommendList21xinnengyuanqiche = res.data.data.list
					}
				});
				this.recommendColumn21xinnengyuanqiche = 'qichefenlei'
			},
			async recommendIndexClick21(index, name) {
				this['recommendIndex21' + name] = index
				await this.getList()
			},


			handleScroll() {
				let arr = [
					{id:'about',css:'animate__'},
					{id:'system',css:'animate__'},
					{id:'animate_recommendxinnengyuanqiche',css:'animate__'},
					{id:'animate_listxinnengyuanqiche',css:'animate__'},
					{id:'animate_listremenqiche',css:'animate__'},
					{id:'animate_newsnews',css:'animate__'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			preHttp2(str) {
				return str && str.split(',w').length>1;
			},
			getNewsList() {
				let data = {
					page: 1,
					limit: 4,
					sort: 'addtime',
					order: 'desc'
				}
				this.$http.get('news/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
					
					}
				});
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
				autoSortUrl = "xinnengyuanqiche/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "xinnengyuanqiche/autoSort2";
				}
				data = {
					page: 1,
					limit: 8,
					onshelves: 1,
				}
				if(this.recommendIndex21xinnengyuanqiche != 0){
					data.qichefenlei = this.recommendList21xinnengyuanqiche[this.recommendIndex21xinnengyuanqiche - 1].qichefenlei
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.xinnengyuanqicheRecommend = res.data.data.list;
					}
				});
			
				data = {
					page: 1,
					limit: 5,
					onshelves: 1,
				}

				this.$http.get('xinnengyuanqiche/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.xinnengyuanqicheList = res.data.data.list;
					}
				});
				data = {
					page: 1,
					limit: 5,
					onshelves: 1,
				}

				this.$http.get('remenqiche/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.remenqicheList = res.data.data.list;
					}
				});
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 0px auto;
		flex-direction: column;
		background: #fff;
		display: flex;
		width: 100%;
		.news {
			padding: 0;
			margin: 0;
			background: url(http://codegen.caihongy.cn/20250103/5efd25a6df7c4bed92416b2d2de8f137.jpg) no-repeat center top / 100% 100%;
			width: 100%;
			position: relative;
			text-align: center;
			order: 11;
			.news_title_box {
				padding: 0;
				margin: 0;
				background: url(http://codegen.caihongy.cn/20241228/f151b89f53c94b7fa5ae510c2f7bd563.png) no-repeat center bottom;
				width: 100%;
				position: relative;
				text-align: center;
				height: 191px;
				.news_title {
					margin: 0 auto;
					color: #fff;
					background: none;
					font-weight: 600;
					display: block;
					letter-spacing: 1px;
					width: auto;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
				}
				.news_subhead {
					margin: 0;
					color: #999;
					display: none;
					width: 100%;
					font-size: 18px;
					line-height: 40px;
					text-align: center;
				}
			}
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list5 {
				padding: 0;
				margin: 20px auto;
				color: #888;
				background: none;
				width: 1200px;
				font-size: 14px;
				text-align: left;
				height: auto;
				.list-item {
					cursor: pointer;
					border: 4px solid #dfb858;
					padding: 20px;
					margin: 0 0 25px;
					background: #fff;
					width: 100%;
					height: auto;
					img {
						object-fit: cover;
						width: 275px;
						float: left;
						height: 275px;
					}
					.infoBox {
						padding: 10px;
						align-content: center;
						background: none;
						display: inline-flex;
						width: calc(100% - 275px);
						align-items: center;
						flex-wrap: wrap;
						height: 275px;
						.name {
							padding: 10px;
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							font-weight: 600;
							width: 100%;
							font-size: 16px;
							line-height: 1.5;
							text-overflow: ellipsis;
						}
						.desc {
							padding: 10px;
							max-height: 150px;
							overflow: hidden;
							color: #666;
							width: 100%;
							font-size: 14px;
							line-height: 30px;
							height: auto;
						}
						.time_item {
							padding: 0 10px;
							margin: 5px 0;
							color: #555;
							display: inline-block;
							width: 100%;
							order: 11;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.publisher_item {
							padding: 0 10px;
							color: #214ea1;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.like_item {
							padding: 0 10px;
							color: #ea8021;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.collect_item {
							padding: 0 10px;
							color: #179da9;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								color: #666;
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.view_item {
							padding: 0 10px;
							color: #f00;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								color: #666;
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
					}
				}
				.list-body {
					background: none;
					display: flex;
					width: 100%;
					justify-content: space-between;
					flex-wrap: wrap;
					height: 275px;
					.list-item1 {
						cursor: pointer;
						border: 4px solid #3397da;
						padding: 20px 10px 10px 20px;
						align-content: center;
						background: #fff;
						display: flex;
						width: 32%;
						align-items: center;
						position: relative;
						flex-wrap: wrap;
						height: 275px;
						.name {
							padding: 0 10px 0 0;
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							font-weight: 600;
							width: 100%;
							font-size: 16px;
							line-height: 1.5;
							text-overflow: ellipsis;
						}
						.desc {
							padding: 10px 10px 10px 0;
							overflow: hidden;
							color: #666;
							width: 100%;
							font-size: 14px;
							line-height: 28px;
							height: 150px;
						}
						.time_item {
							padding: 0 0px;
							margin: 5px 0;
							color: #555;
							display: inline-block;
							width: 100%;
							order: 11;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.publisher_item {
							padding: 0 10px 0 0;
							color: #214ea1;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.like_item {
							padding: 0 10px 0 0;
							color: #ea8021;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.collect_item {
							padding: 0 10px 0 0;
							color: #179da9;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.view_item {
							padding: 0 10px 0 0;
							color: #f00;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
					}
					.list-item2 {
						cursor: pointer;
						border: 4px solid #3397da;
						padding: 20px 10px 10px 20px;
						align-content: center;
						background: #fff;
						display: flex;
						width: 32%;
						align-items: center;
						position: relative;
						flex-wrap: wrap;
						height: 275px;
						.name {
							padding: 0 10px 0 0;
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							font-weight: 600;
							width: 100%;
							font-size: 16px;
							line-height: 1.5;
							text-overflow: ellipsis;
						}
						.desc {
							padding: 10px 10px 10px 0;
							overflow: hidden;
							color: #666;
							width: 100%;
							font-size: 14px;
							line-height: 28px;
							height: 150px;
						}
						.time_item {
							padding: 0 0px;
							margin: 5px 0;
							color: #555;
							display: inline-block;
							width: 100%;
							order: 11;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.publisher_item {
							padding: 0 10px 0 0;
							color: #214ea1;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.like_item {
							padding: 0 10px 0 0;
							color: #ea8021;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.collect_item {
							padding: 0 10px 0 0;
							color: #179da9;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.view_item {
							padding: 0 10px 0 0;
							color: #f00;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
					}
					.list-item3 {
						cursor: pointer;
						border: 4px solid #3397da;
						padding: 20px 10px 10px 20px;
						align-content: center;
						background: #fff;
						display: flex;
						width:  32%;
						align-items: center;
						position: relative;
						flex-wrap: wrap;
						height: 275px;
						.name {
							padding: 0 10px 0 0;
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							font-weight: 600;
							width: 100%;
							font-size: 16px;
							line-height: 1.5;
							text-overflow: ellipsis;
						}
						.desc {
							padding: 10px 10px 10px 0;
							overflow: hidden;
							color: #666;
							width: 100%;
							font-size: 14px;
							line-height: 28px;
							height: 150px;
						}
						.time_item {
							padding: 0 0px;
							margin: 5px 0;
							color: #555;
							display: inline-block;
							width: 100%;
							order: 11;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.publisher_item {
							padding: 0 10px 0 0;
							color: #214ea1;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.like_item {
							padding: 0 10px 0 0;
							color: #ea8021;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.collect_item {
							padding: 0 10px 0 0;
							color: #179da9;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
						.view_item {
							padding: 0 10px 0 0;
							color: #f00;
							display: inline-block;
							.icon {
								margin: 0 2px 0 0;
								line-height: 1.5;
							}
							.label {
								display: none;
								line-height: 1.5;
							}
							.text {
								line-height: 1.5;
							}
						}
					}
				}
			}
			.moreBtn {
				border: 0px solid #999;
				cursor: pointer;
				border-radius: 10px;
				padding: 0 20px;
				margin: 20px auto 40px;
				background: #3397da;
				display: inline-block;
				width: 250px;
				line-height: 48px;
				text-align: center;
				.text {
					color: #fff;
					font-size: 16px;
				}
				.icon {
					color: #333;
					display: none;
					font-size: 15px;
				}
			}
		}
		.recommend {
			padding: 0 0 10px;
			margin: 0px auto;
			background: url(http://codegen.caihongy.cn/20241228/80c792bd909b471aa43fff826e4d14d7.jpg) no-repeat center bottom / 100% auto;
			width: 100%;
			position: relative;
			text-align: center;
			order: 1;
			.recommend_title_box {
				padding: 0;
				margin: 0;
				background: url(http://codegen.caihongy.cn/20241228/f151b89f53c94b7fa5ae510c2f7bd563.png) no-repeat center bottom;
				width: 100%;
				position: relative;
				text-align: center;
				height: 191px;
				.recommend_title {
					margin: 0 auto;
					color: #fff;
					background: none;
					font-weight: 600;
					display: block;
					letter-spacing: 1px;
					width: auto;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
				}
				.recommend_subhead {
					margin: 0;
					color: #999;
					display: none;
					width: 100%;
					font-size: 18px;
					line-height: 40px;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list21 {
				border: 0;
				padding: 0;
				background: none;
				width: 100%;
				text-align: left;
				.list-body-top {
					padding: 60px 20px 180px;
					margin: 0 auto;
					background: url(http://codegen.caihongy.cn/20240809/ce61590e38bc47b0b2b556bdeaa52d38.jpg) no-repeat center bottom / cover;
					display: none;
					width: 100%;
					justify-content: center;
					align-items: center;
					.tab-title {
						margin: 0 40px 0 0;
						color: #fff;
						display: none;
						font-size: 28px;
						line-height: 34px;
						height: 38px;
					}
					.tab_view {
						display: flex;
						.tab {
							cursor: pointer;
							padding: 0 30px;
							margin: 2px 0 0;
							color: #fff;
							font-size: 16px;
							line-height: 34px;
							height: 34px;
						}
						.tab::before {
						}
						.tab:hover {
							color: #000;
							background: #fff;
							position: relative;
						}
						.tab:hover::before {
							border: 1px solid #ddd;
							margin: 0 0 0 -6px;
							left: 50%;
							bottom: -6px;
							display: inline-block;
							border-color: #fff transparent;
							border-width: 6px 6px 0;
							position: absolute;
							content: "";
						}
						.tab.tabActive:hover {
							color: #000;
							background: #fff;
							position: relative;
						}
						.tab.tabActive:hover::before {
							border: 1px solid #fff;
							margin: 0 0 0 -6px;
							left: 50%;
							bottom: -6px;
							display: inline-block;
							border-color: #fff transparent;
							border-width: 6px 6px 0;
							position: absolute;
							content: "";
						}
					}
					.tab-right {
						cursor: pointer;
						display: none;
						line-height: 38px;
						height: 38px;
						.text {
							color: #fff;
							font-size: 16px;
						}
						.icon {
							color: #f5f5f5;
							font-size: 12px;
						}
					}
				}
				.list-body {
					padding: 0;
					margin: 50px auto 0;
					display: flex;
					width: 1200px;
					justify-content: space-between;
					.list-item {
						padding: 0 0 60px;
						margin: 0;
						background: #fff;
						width: 23.5%;
						position: relative;
						.img {
							width: 100%;
							height: 300px;
							img {
								object-fit: cover;
								width: 100%;
								height: 100%;
							}
						}
						.infoBox {
							border: 1px solid #ddd;
							padding: 20px;
							left: 0;
							background: #fff;
							bottom: 0;
							border-width: 0 1px 1px;
							position: absolute;
							right: 0;
							.name {
								margin: 0 0 0px;
								overflow: hidden;
								color: #333;
								white-space: nowrap;
								font-weight: 600;
								display: block;
								width: 100%;
								font-size: 16px;
								line-height: 28px;
								text-overflow: ellipsis;
								text-align: left;
							}
							.bottomInfo {
								overflow: hidden;
								transition: all 0.6s;
								height: 0;
								.price {
									margin: 10px auto 4px;
									color: #f00;
									width: 100%;
									font-size: 18px;
									line-height: 24px;
								}
								.time_item {
									padding: 0;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.publisher_item {
									padding: 0;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.like_item {
									padding: 0;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.collect_item {
									padding: 0;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
								.view_item {
									padding: 0;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										display: none;
										font-size: inherit;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: inherit;
										line-height: 1.5;
									}
								}
							}
						}
					}
					.list-item::after {
						bottom: 0;
						background: #ffcb2d;
						width: 0;
						position: absolute;
						content: "";
						transition: all 0.6s;
						height: 5px;
					}
					.list-item:hover {
						box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
						.infoBox {
							.bottomInfo {
								cursor: pointer;
								height: 200px;
							}
						}
					}
					.list-item:hover::after {
						width: 100%;
					}
				}
			}
			.moreBtn {
				border: 0px solid #999;
				cursor: pointer;
				border-radius: 10px;
				padding: 0 20px;
				margin: 30px auto;
				background: #3397da;
				display: inline-block;
				width: 250px;
				line-height: 48px;
				text-align: center;
				.text {
					color: #fff;
					font-size: 16px;
				}
				.icon {
					color: #fff;
					display: none;
					font-size: 15px;
				}
			}
		}
		.lists {
			padding: 0;
			margin: 0;
			background: url() no-repeat center center;
			width: 100%;
			position: relative;
			text-align: center;
			order: 7;
			.list_title_box {
				padding: 0;
				margin: 0;
				background: url(http://codegen.caihongy.cn/20241228/f151b89f53c94b7fa5ae510c2f7bd563.png) no-repeat center bottom;
				width: 100%;
				position: relative;
				text-align: center;
				height: 191px;
				.list_title {
					margin: 0 auto;
					color: #fff;
					background: none;
					font-weight: 600;
					display: block;
					letter-spacing: 1px;
					width: auto;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
				}
				.list_subhead {
					margin: 0;
					color: #999;
					display: none;
					width: 100%;
					font-size: 18px;
					line-height: 40px;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list9 {
				padding: 0;
				margin: 40px auto 20px;
				color: #fff;
				align-content: flex-start;
				background: none;
				display: flex;
				width: 1200px;
				font-size: 14px;
				justify-content: space-between;
				align-items: flex-start;
				text-align: left;
				height: auto;
				.list-body-top {
					margin: 0 10px 0 0;
					width: calc(50% - 10px);
					height: auto;
					.list-item1 {
						cursor: pointer;
						margin: 0 0 16px;
						display: inline-block;
						width: 100%;
						position: relative;
						height: 580px;
						img {
							object-fit: cover;
							width: 100%;
							float: left;
							height: 100%;
						}
						.infoBox {
							padding: 5px 0 10px;
							left: 0;
							bottom: 0;
							background: rgba(0,0,0,.3);
							width: 100%;
							position: absolute;
							.name {
								padding: 0 10px;
								overflow: hidden;
								color: #fff;
								white-space: nowrap;
								width: 100%;
								font-size: 15px;
								line-height: 30px;
								text-overflow: ellipsis;
							}
							.price {
								padding: 0 10px;
								color: #f00;
								font-size: 20px;
								line-height: 30px;
							}
							.time_item {
								padding: 0 0 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.publisher_item {
								padding: 0 0 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.like_item {
								padding: 0 0 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.collect_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.view_item {
								padding: 0 0 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
						}
					}
					.list-item2 {
						cursor: pointer;
						margin: 0 0 20px;
						display: inline-block;
						width: 100%;
						position: relative;
						height: 580px;
						img {
							object-fit: cover;
							width: 100%;
							float: left;
							height: 100%;
						}
						.infoBox {
							padding: 5px 0 10px;
							left: 0;
							bottom: 0;
							background: rgba(0,0,0,.3);
							width: 100%;
							position: absolute;
							.name {
								padding: 0 10px;
								overflow: hidden;
								color: #fff;
								white-space: nowrap;
								width: 100%;
								font-size: 15px;
								line-height: 30px;
								text-overflow: ellipsis;
							}
							.price {
								padding: 0 10px;
								color: #f00;
								font-size: 20px;
								line-height: 30px;
							}
							.time_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.publisher_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.like_item {
								padding: 0 0 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.collect_item {
								padding: 0 0 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.view_item {
								padding: 0 0 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
						}
					}
				}
				.list-body-bottom {
					margin: 0 0 0 10px;
					display: flex;
					width: calc(50% - 10px);
					justify-content: space-between;
					flex-wrap: wrap;
					height: auto;
					.list-item1 {
						cursor: pointer;
						margin: 0 0 20px;
						width: 100%;
						position: relative;
						height: 380px;
						img {
							object-fit: cover;
							width: 100%;
							float: left;
							height: 100%;
						}
						.infoBox {
							padding: 5px 0 10px;
							left: 0;
							bottom: 0;
							background: rgba(0,0,0,.3);
							width: 100%;
							position: absolute;
							.name {
								padding: 0 10px;
								overflow: hidden;
								color: #fff;
								white-space: nowrap;
								width: 100%;
								font-size: 15px;
								line-height: 30px;
								text-overflow: ellipsis;
							}
							.price {
								padding: 0 10px;
								color: #f00;
								font-size: 20px;
								line-height: 30px;
							}
							.time_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.publisher_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									color: #fff;
									font-size: 12px;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									color: #fff;
									font-size: 12px;
									line-height: 1.5;
								}
							}
							.like_item {
								padding: 0 0 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.collect_item {
								padding: 0 0 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.view_item {
								padding: 0 0 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
						}
					}
					.list-item2 {
						cursor: pointer;
						margin: 0 0 20px;
						width: 100%;
						position: relative;
						height: 380px;
						img {
							object-fit: cover;
							width: 100%;
							float: left;
							height: 100%;
						}
						.infoBox {
							padding: 5px 0 10px;
							left: 0;
							bottom: 0;
							background: rgba(0,0,0,.3);
							width: 100%;
							position: absolute;
							.name {
								padding: 0 10px;
								overflow: hidden;
								color: #fff;
								white-space: nowrap;
								width: 100%;
								font-size: 15px;
								line-height: 30px;
								text-overflow: ellipsis;
							}
							.price {
								padding: 0 10px;
								color: #f00;
								font-size: 20px;
								line-height: 30px;
							}
							.time_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.publisher_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.like_item {
								padding: 0 0 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.collect_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.view_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
						}
					}
					.list-item3 {
						cursor: pointer;
						margin: 0 0 20px;
						width: 100%;
						position: relative;
						height: 380px;
						img {
							object-fit: cover;
							width: 100%;
							float: left;
							height: 100%;
						}
						.infoBox {
							padding: 5px 0 10px;
							left: 0;
							bottom: 0;
							background: rgba(0,0,0,.3);
							width: 100%;
							position: absolute;
							.name {
								padding: 0 10px;
								overflow: hidden;
								color: #fff;
								white-space: nowrap;
								width: 100%;
								font-size: 15px;
								line-height: 30px;
								text-overflow: ellipsis;
							}
							.price {
								padding: 0 10px;
								color: #f00;
								font-size: 20px;
								line-height: 30px;
							}
							.time_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.publisher_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.like_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.collect_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.view_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
						}
					}
				}
			}
			.moreBtn {
				border: 0px solid #999;
				cursor: pointer;
				border-radius: 10px;
				padding: 0 20px;
				margin: 0px auto 40px;
				background: #3397da;
				display: inline-block;
				width: 250px;
				line-height: 48px;
				text-align: center;
				.text {
					color: #fff;
					font-size: 16px;
				}
				.icon {
					color: #fff;
					font-size: 16px;
				}
			}
		}
	}
</style>
