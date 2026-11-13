# Backend Playground 学习进度

面向「前端转后端」的 Spring Boot 练习项目。换电脑后先读本文件，再从 **「当前进行到哪」** 接着做。

---

## 学习模式（和 AI 协作约定）

1. **分步路线**：按第 0～N 步小步推进，每步有 **验收标准**（浏览器 / `mvnw test` / H2 Console）。
2. **自己写代码为主**：AI 给 **完整示例代码 + 分步清单**，**不默认直接改仓库**（除非你明确说「帮我改」）。
3. **先懂再扩**：概念（Maven、Bean、Mapper、Optional 等）用问答讲清；公司项目（`corpwechat-group-qrcode`）作对照，不同时一上来啃业务大项目。
4. **一支专用仓库**：练习代码以 GitHub 仓库为准，本地开发目录与仓库同步（见下）。

---

## 代码与云端在哪

| 项 | 路径 / 地址 |
|----|-------------|
| **Git 远程** | https://github.com/DancingLeaf123/learningRepo.git |
| **项目路径** | 仓库内 `java/backend-playground/` |
| **本地仓库克隆** | `C:\Users\bill.ye\Desktop\projects\learningRepo`（原机器；新机可改路径） |
| **工作区中的副本** | `AI_workingSpace_2\java\learningRepo\...`（若做了目录联接，以你本机实际为准） |
| **入口类** | `BackendPlaygroundApplication` |
| **默认端口** | `8081`（`application.yml`） |

**换电脑第一步：**

```bash
git clone https://github.com/DancingLeaf123/learningRepo.git
# 用 IDEA 打开 learningRepo/java/backend-playground
# 配置 JDK 21，Maven Reload，Run BackendPlaygroundApplication
```

本地未 push 的进度：**第 8 步半成品可能在本地 learningRepo，上机后执行 `git status` / `git pull` 核对。**

---

## 环境备忘（Spring Boot 4）

本仓库用 **Spring Boot 4.1.2**，与网上大量 Boot 3 教程在依赖/包名上略有不同：

| 问题 | 处理 |
|------|------|
| MyBatis 与 Boot 4 | `mybatis-spring-boot-starter` **4.0.0**（不要用 3.0.5） |
| H2 控制台 404 | 额外依赖 `spring-boot-h2console` |
| H2 Console 登录 URL | 与 `spring.datasource.url` 一致：`jdbc:h2:mem:playground;DB_CLOSE_DELAY=-1;MODE=MySQL`，用户 `sa`，密码空 |
| MockMvc 测试 `@AutoConfigureMockMvc` | `org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc` |
| `application.properties` 中文乱码 | 自定义配置放 `application.yml`（UTF-8） |

---

## 已完成（验收通过）

### 第 0～1 步：Maven 壳 + 第一个接口

- 读懂 `pom.xml`、Maven 源码目录约定（`package` 不要写 `main.java`）。
- `GET /hello`（曾用配置问候语 + `HelloService`）。

### 第 2 步：配置

- `application.yml`：`server.port`、`app.greeting` 等。
- `@Value` 已挪到 Service 的构造器注入（当前问候逻辑在 `HelloService`）。

### 第 3 步：分层 + IoC

- `HelloController` → `HelloService`；构造器注入；理解 Bean 扫描、`Optional` 未在本步。

### 第 4 步：统一返回 `Result<T>`

- `common/Result.java`；`GET /hello` 返回 JSON 信封（`code` / `message` / `data`）。

### 第 5 步：测试

- `HelloControllerTest` + MockMvc；`mvnw test` 通过（Mockito 警告可忽略）。

### 第 6 步：H2 + MyBatis

- 数据源、`schema.sql` / `data.sql`、Hikari。
- `Note` → `NoteMapper`（XML）→ `NoteService` → `NoteController`。
- `GET /notes` 返回两条种子数据。
- **选修完成**：`selectAll` 从 `@Select` 迁到 `mapper/NoteMapper.xml`。

### 第 7 步：按 id 查询

- `GET /notes/{id}`；XML `selectById` + `#{id}`；`Optional` + `map` / `orElseGet`。
- **已验收示例**：
  - `/notes/1`、`/notes/2` → `code: 0`，单条 `data`。
  - `/notes/999` → `code: 404`，`data: null`，`message` 含 id。

---

## 当前进行到哪（第 8 步 · 未完）

**目标：`POST /notes` 新增笔记**（`@RequestBody` + `@Valid` + MyBatis `insert` + 返回 `Result<NoteVO>`）。

| 子项 | 状态 |
|------|------|
| `pom.xml` 添加 `spring-boot-starter-validation` | ✅ 已加 |
| 新建 `dto/NoteCreateRequest.java` | 🚧 **进行中**（类未完成，需补全字段与 getter/setter） |
| `NoteMapper` + XML `insert`（`useGeneratedKeys`） | ❌ 未做 |
| `NoteService.create(...)` | ❌ 未做 |
| `NoteController` `@PostMapping` | ❌ 未做 |

**恢复第 8 步时**：在对话里说「继续做第 8 步 POST」，按当时给的 8.0～8.6 清单从 `NoteCreateRequest` 补全开始。

---

## 后续路线图（未开始）

| 步 | 内容 |
|----|------|
| **9** | `@RestControllerAdvice`：业务异常 + **校验失败** 也统一成 `Result` 形态 |
| **10** | MockMvc 覆盖 `/notes`、`/notes/{id}`、POST |
| **11** | XML 动态 SQL：如 `GET /notes?keyword=` |
| **12** | 对照读公司项目：挑 `corpwechat-group-qrcode` 一条 API 追到 Mapper/XML |

---

## 项目结构速记（学到哪用到哪）

```text
src/main/java/com/learning/backend_playground/
  BackendPlaygroundApplication.java   @SpringBootApplication + @MapperScan
  controller/     HTTP
  service/        业务
  mapper/         MyBatis 接口
  entity/         表行映射
  dto/            入参 / 出参 VO
  common/         Result<T>

src/main/resources/
  application.yml
  schema.sql, data.sql
  mapper/NoteMapper.xml
```

---

## 关键概念笔记（自测用）

- **POM**：Project Object Model；`pom.xml` = Maven 项目说明书。
- **Bean**：Spring 容器管理的单例组件（`@Service`、`@RestController`）。
- **Mapper**：SQL ↔ Java 对象；接口 + XML（或注解）。
- **`#{}`**：参数绑定；**`${}`** 仅安全场景（如白名单列名），不对用户输入用 `${}`。
- **`Result<T>` 泛型**：编译期类型约定；运行时是普通 POJO。
- **Optional + map/orElseGet**：有值则转换，无值则 `Result.fail`。

---

## 上次更新

- **日期**：2026-11-13  
- **状态**：第 7 步完成；第 8 步刚开头（validation 依赖 + 未完成的 `NoteCreateRequest`）。

换机后：`git pull` → 打开本文 → IDEA Run → 确认 `GET /notes` 仍正常 → 从第 8 步继续。
