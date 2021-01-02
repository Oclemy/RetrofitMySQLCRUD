package info.camposha.retrofitmysqlcrud.Views;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import info.camposha.retrofitmysqlcrud.Helpers.MyAdapter;
import info.camposha.retrofitmysqlcrud.Helpers.Utils;
import info.camposha.retrofitmysqlcrud.R;
import info.camposha.retrofitmysqlcrud.Retrofit.ResponseModel;
import info.camposha.retrofitmysqlcrud.Retrofit.RestApi;
import info.camposha.retrofitmysqlcrud.Retrofit.Scientist;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static info.camposha.retrofitmysqlcrud.Helpers.Utils.hideProgressBar;
import static info.camposha.retrofitmysqlcrud.Helpers.Utils.show;
import static info.camposha.retrofitmysqlcrud.Helpers.Utils.showInfoDialog;
import static info.camposha.retrofitmysqlcrud.Helpers.Utils.showProgressBar;

public class ScientistsActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener,MenuItem.OnActionExpandListener{

     //We define our instance fields
    private RecyclerView rv;
    private MyAdapter mAdapter;
    private LinearLayoutManager layoutManager;
    public ArrayList<Scientist> allPagesScientists = new ArrayList();
    private Boolean isScrolling = false;
    private int currentScientists, totalScientists, scrolledOutScientists;
    private ProgressBar mProgressBar;

    /**
     * We initialize our widgets
     */
    private void initializeViews() {
        mProgressBar = findViewById(R.id.mProgressBarLoad);
        mProgressBar.setIndeterminate(true);
        showProgressBar(mProgressBar);
        rv = findViewById(R.id.mRecyclerView);
    }

    /**
     * This method will setup oir RecyclerView
     */
    private void setupRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        mAdapter = new MyAdapter(allPagesScientists);
        rv.setAdapter(mAdapter);
        rv.setLayoutManager(layoutManager);
    }
    /**
     * This method will download for us data from php mysql based on supplied query string
     * as well as pagination parameters. We are basiclally searching or selecting data
     * without seaching. However all the arriving data is paginated at the server level.
     */
    private void retrieveAndFillRecyclerView(final String action, String queryString,
     final String start, String limit) {

        mAdapter.searchString = queryString;
        RestApi api = Utils.getClient().create(RestApi.class);
        Call<ResponseModel> retrievedData;

        if (action.length() > 0) {
            showProgressBar(mProgressBar);
            retrievedData = api.search(action, queryString, start, limit);
        } else {
            allPagesScientists.clear();
            showProgressBar(mProgressBar);
            retrievedData = api.retrieve();
        }
        retrievedData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel>
             response) {
                if(response == null || response.body() == null){
                    showInfoDialog(ScientistsActivity.this,"ERROR","Response or Response Body is null. \n Recheck Your PHP code.");
                    return;
                }
                List<Scientist> currentPageScientists = response.body().getResult();

                if (currentPageScientists != null && currentPageScientists.size() > 0) {
                    if (action.equalsIgnoreCase("GET_PAGINATED_SEARCH")) {
                        allPagesScientists.clear();
                    }
                    allPagesScientists.addAll(currentPageScientists);
                   // show(ScientistsActivity.this,currentPageScientists.size()+" Scientists Found");
                } else {
                    if (action.equalsIgnoreCase("GET_PAGINATED_SEARCH")) {
                        allPagesScientists.clear();
                    }
                    show(ScientistsActivity.this,"Hey! Reached End. No more Found");
                }
                mAdapter.notifyDataSetChanged();
                hideProgressBar(mProgressBar);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                hideProgressBar(mProgressBar);
                Log.d("RETROFIT", "ERROR: " + t.getMessage());
                Utils.showInfoDialog(ScientistsActivity.this, "ERROR", t.getMessage());
            }
        });
    }
    /**
     * We will listen to scroll events. This is important as we are implementing scroll to
     * load more data pagination technique
     */
    private void listenToRecyclerViewScroll() {
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //when scrolling starts
                super.onScrollStateChanged(recyclerView, newState);
                //check for scroll state
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // When the scrolling has stopped
                super.onScrolled(recyclerView, dx, dy);
                currentScientists = layoutManager.getChildCount();
                totalScientists = layoutManager.getItemCount();
                scrolledOutScientists = ((LinearLayoutManager) recyclerView.getLayoutManager()).
                findFirstVisibleItemPosition();

                if (isScrolling && (currentScientists + scrolledOutScientists ==
                 totalScientists)) {
                    isScrolling = false;

					if (dy > 0) {
						// Scrolling up
                        retrieveAndFillRecyclerView("GET_PAGINATED",
                         mAdapter.searchString,
                         String.valueOf(totalScientists), "7");

					} else {
						// Scrolling down
					}
                }
            }
        });
    }
 /**
     * We inflate our menu. We show SearchView inside the toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.scientists_page_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setIconified(true);
        searchView.setQueryHint("Search");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new:
                Utils.openActivity(this, CRUDActivity.class);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        retrieveAndFillRecyclerView("GET_PAGINATED_SEARCH", query, "0", "5");
        return false;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return false;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientists);

        initializeViews();
        this.listenToRecyclerViewScroll();
        setupRecyclerView();
        retrieveAndFillRecyclerView("GET_PAGINATED", "", "0", "7");
    }
}

//end
