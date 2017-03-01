package com.demo.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TAttach entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_attach", catalog = "itsys")
public class TAttach implements java.io.Serializable {

	// Fields

		private Integer id;
		private TOrder TOrder;
		private String attachName;
		private String serverName;
		private String url;
		private Integer uploadMan;
		private Timestamp uploadTime;
		private String tag;
		private String status;

		// Constructors

		/** default constructor */
		public TAttach() {
		}

		/** full constructor */
		public TAttach(TOrder TOrder, String attachName, String serverName,
				String url, Integer uploadMan, Timestamp uploadTime, String tag,
				String status) {
			this.TOrder = TOrder;
			this.attachName = attachName;
			this.serverName = serverName;
			this.url = url;
			this.uploadMan = uploadMan;
			this.uploadTime = uploadTime;
			this.tag = tag;
			this.status = status;
		}

		// Property accessors
		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		public Integer getId() {
			return this.id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "order_id")
		public TOrder getTOrder() {
			return this.TOrder;
		}

		public void setTOrder(TOrder TOrder) {
			this.TOrder = TOrder;
		}

		@Column(name = "attach_name", length = 200)
		public String getAttachName() {
			return this.attachName;
		}

		public void setAttachName(String attachName) {
			this.attachName = attachName;
		}

		@Column(name = "server_name", length = 200)
		public String getServerName() {
			return this.serverName;
		}

		public void setServerName(String serverName) {
			this.serverName = serverName;
		}

		@Column(name = "url", length = 1000)
		public String getUrl() {
			return this.url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		@Column(name = "upload_man")
		public Integer getUploadMan() {
			return this.uploadMan;
		}

		public void setUploadMan(Integer uploadMan) {
			this.uploadMan = uploadMan;
		}

		@Column(name = "upload_time", length = 19)
		public Timestamp getUploadTime() {
			return this.uploadTime;
		}

		public void setUploadTime(Timestamp uploadTime) {
			this.uploadTime = uploadTime;
		}

		@Column(name = "tag", length = 20)
		public String getTag() {
			return this.tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}

		@Column(name = "status", length = 2)
		public String getStatus() {
			return this.status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	}